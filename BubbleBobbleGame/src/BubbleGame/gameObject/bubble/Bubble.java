package BubbleGame.gameObject.bubble;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import BubbleGame.gameObject.Block;
import BubbleGame.gameObject.Coordinates;
import BubbleGame.gameObject.Player;
import BubbleGame.gameObject.SpriteBase;
import BubbleGame.gameObject.monster.Monster;
import BubbleGame.GamePanel;
import utility.Settings;

public class Bubble extends JLabel {
	private double xStartLocation; // Player의 X 위치
	private double yStartLocation; // Player의 Y 위치
	private int moveDirection; // bubble 움직임 방향 (1: right, -1: left)
	private long beforeTime; // bubble 생성 시간
	private int bubbleState = 0; // bubble의 상태 확인
	private boolean monsterCapture = false;
	private boolean blockMeetCheck = false;
	private int count = 0;
	
	private String bubbleGreenPath = "src/image/bubble-green";
	private String BubbleBurstPath = "src/image/bubble-green-burst";
	private String BubbleRedPath = "src/image/bubble-red";

	public void setBubblePath(String monsterName) {
		this.bubbleGreenPath = "src/image/bubble-" + monsterName + "-green";
		BubbleRedPath = "src/image/bubble-" + monsterName + "-red";
		spriteBase.setDirPath(this.bubbleGreenPath);
	}

	private Coordinates coordinate; // 위치 설정 도와주는 class
	private SpriteBase spriteBase; // 이미지 설정을 도와주는 class
	// private BubbleThread bubbleThread; // 버블 움직임을 도와주는 thread

	/* Bubble 생성자 : 기본 설정 및 이미지 설정 --> 스레드 실행 */
	public Bubble(double xStartLocation, double yStartLocation, int moveDirection) {
		super();
		this.moveDirection = moveDirection;
		this.xStartLocation = xStartLocation + (Settings.SPRITE_SIZE + 10) * this.moveDirection;
		this.yStartLocation = yStartLocation;
		this.coordinate = new Coordinates(this.xStartLocation, this.yStartLocation, 1, 
				Settings.BUBBLE_INIT_SPEED * this.moveDirection, 0, 1);
		this.spriteBase = new SpriteBase(bubbleGreenPath, coordinate);

		this.spriteBase.setHeight(Settings.BUBBLE_SIZE);
		this.spriteBase.setWidth(Settings.BUBBLE_SIZE);
		this.spriteBase.setOperationTime(10);
		getImagePaths(); // imagePath 알아내기
		this.setSize((int) Settings.BUBBLE_SIZE, (int) Settings.BUBBLE_SIZE);
		// bubbleThread = new BubbleThread(this); // thread 제작 후 실행
		// bubbleThread.start();
		beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
	}
	
	/* 이미지 Path 설정 */
	private void getImagePaths() {
		String dirPath = spriteBase.getDirPath();
		File dir = new File(dirPath);

		this.spriteBase.setImagePaths(dir.list());
	}

	/* img 알아내기(전달) */
	public Image getImage() {
		String path = this.spriteBase.getImage();
		Image image = new ImageIcon(path).getImage();
		return image;
	}

	/* x축(양 옆) 벽과 만났는지 확인(만났으면 true) */
	private boolean checkWallMeetX() {
		if (getX() <= 0) { // 왼쪽 벽
			this.coordinate.setXCoordinate(0);
			return true;
		} else if (this.getParent() != null && getX() >= this.getParent().getWidth() - Settings.BUBBLE_SIZE) { // 오른쪽 벽
			this.coordinate.setXCoordinate(this.getParent().getWidth() - Settings.BUBBLE_SIZE);
			return true;
		}
		return false;
	}

	/* 천장 벽과 만났는지 확인(만났으면 true) */
	private boolean checkWallMeetY() {
		if (blockMeetCheck || getY() <= 0 + Settings.BLOCK_HEIGHT) {
			Random random = new Random(); // 랜덤 객체 생성
			if (count % 3 == 0) {
				if (random.nextInt(5) > 1)
					moveDirection = 1;
				else {
					moveDirection = -1;
					this.coordinate.setYCoordinate(getY() + (1));
				}
				//this.coordinate.setXCoordinate(getX() + (2 * moveDirection));
				coordinate.setDYCoordinate(0);
				coordinate.setDXCoordinate((2 * moveDirection));
				spriteBase.move();
			}
			count++;
			return true;
		}
		return false;
	}
	
	/* Bubble과 label이 닿았는지 확인하는 함수 */
	public boolean checkBubbleMit(JLabel label) {
		if (wallCollision (label.getX(), label.getX() + label.getWidth(), label.getY(),
				label.getY() + label.getHeight())) {
			//System.out.println("checkBubbleMeet");
			if (label instanceof Monster) {
				if (!monsterCapture) {
					String monsterName = ((Monster) label).getMonsterName();
					setBubblePath(monsterName);
					monsterCapture = true; // 몬스터 잡았음 true
				} else {
					return false;
				}
			} else if (label instanceof Player) {

				if (bubbleState != 2) {
					if (!((Player) label).isJumping() && !((Player) label).isMoveDown()) {
						if (((Player) label).isMoveLeft() && !checkWallMeetX()) {
							this.moveDirection = -1;
							this.coordinate.setXCoordinate(label.getX() - getWidth());
							return false;
						} else if (((Player) label).isMoveRight() && !checkWallMeetX()) {
							this.moveDirection = 1;
							this.coordinate.setXCoordinate(label.getX() + label.getWidth());
							return false;
						}
						return false;
					}
					beforeTime = (long) (System.currentTimeMillis() - Settings.BUBBLE_LIVE_TIME); // 코드 실행 전에 시간 받아오기
					spriteBase.setDirPath(BubbleBurstPath);
					bubbleState = 2;
				}
			}
			return true;
		}
		return false;
	}

	private void moveBubble() {
		long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffTime = (afterTime - beforeTime); // 두 시간에 차 계산

		if (bubbleState != 2) {
			// 버블이 green일 때 (BubbleState == 0)
			if (bubbleState == 0 && secDiffTime >= Settings.BUBBLE_POWERUP_FLY_TIME) {
				spriteBase.setDirPath(BubbleRedPath);
				bubbleState = 1;
			}
			// 버블이 red일 때 (BubbleState == 1)
			else if (bubbleState == 1 && secDiffTime >= Settings.BUBBLE_LIVE_TIME) {
				spriteBase.setDirPath(BubbleBurstPath);
				bubbleState = 2;
			}
			// 터지기 전에 버블의 움직임 설정
			if (!checkWallMeetX() && !monsterCapture && secDiffTime <= Settings.BUBBLE_FLY_TIME) {
				//coordinate.setDYCoordinate(0);
				//coordinate.setDXCoordinate(Settings.BUBBLE_INIT_SPEED * this.moveDirection);
				spriteBase.move();
			}
			else if (!checkWallMeetY()) {
				coordinate.setDYCoordinate(-Settings.BUBBLE_FLY_SPEED);
				coordinate.setDXCoordinate(0);
				spriteBase.move();
			}
			
		}
		// 버블이 터질 때 움직임 (BubbleState == 2)
		else {
			if (secDiffTime >= Settings.BUBBLE_BURST_TIME) {
				if (monsterCapture)
					((GamePanel) this.getParent()).addScore(100);
				this.getParent().remove(this);
			}
		}
	}
	
	public boolean wallCollision(double minX, double maxX, double minY, double maxY) {
		return spriteBase.causesCollision(minX, maxX, minY, maxY);
	}

	@Override
	public int getX() {
		return (int) this.spriteBase.getXCoordinate();
	}

	@Override
	public int getY() {
		return (int) this.spriteBase.getYCoordinate();
	}

	@Override
	public void paintComponent(Graphics g) {
		moveBubble();
		
		Image bubble = getImage();
		g.drawImage(bubble, 0, 0, this.getWidth(), this.getHeight(), this);

	}
}