package Domain.Abilities;

public class Bullet {

	private double bullet1PosX;
	private double bullet1PosY;
	private double bullet2PosX;
	private double bullet2PosY;
	private double bulletVelX;
	private double bulletVelY;
	private double totalVel;
	private double bullet1Height;
	private double bullet1Width;
	private double bullet2Height;
	private double bullet2Width;
	MagicalHex magicalHex = MagicalHex.getInstance();

	public Bullet() {
		bullet1Height = 10;
		bullet1Width = 10;
		bullet2Height = 10;
		bullet2Width = 10;
		bulletVelY = 5;
		bullet1PosX = magicalHex.getCannon1PosX();
		bullet1PosY = magicalHex.getCannon1PosY();
		bullet2PosX = magicalHex.getCannon2PosX();
		bullet2PosY = magicalHex.getCannon2PosY();
		totalVel = Math.pow(Math.pow(getBulletVelX(), 2) + Math.pow(getBulletVelY(), 2), 0.5);
		setBulletVelX((magicalHex.getCannon1PosY() - magicalHex.getCannon2PosY()) / 11);
	}
	
	
	public void fire() {
		setBullet1PosY(getBullet1PosY() - getBulletVelY());
		setBullet2PosY(getBullet2PosY() - getBulletVelY());
		
		
		setBullet1PosX(getBullet1PosX() - getBulletVelX());
		setBullet2PosX(getBullet2PosX() - getBulletVelX());
	}

	public double getBullet1PosX() {
		return bullet1PosX;
	}

	public void setBullet1PosX(double bullet1PosX) {
		this.bullet1PosX = bullet1PosX;
	}

	public double getBullet1PosY() {
		return bullet1PosY;
	}

	public void setBullet1PosY(double bullet1PosY) {
		this.bullet1PosY = bullet1PosY;
	}

	public double getBullet2PosX() {
		return bullet2PosX;
	}

	public void setBullet2PosX(double bullet2PosX) {
		this.bullet2PosX = bullet2PosX;
	}

	public double getBullet2PosY() {
		return bullet2PosY;
	}

	public void setBullet2PosY(double bullet2PosY) {
		this.bullet2PosY = bullet2PosY;
	}

	public double getBulletVelX() {
		return bulletVelX;
	}

	public void setBulletVelX(double bulletVelX) {
		this.bulletVelX = bulletVelX;
	}

	public double getBulletVelY() {
		return bulletVelY;
	}

	public void setBulletVelY(double bulletVelY) {
		this.bulletVelY = bulletVelY;
	}

	public double getTotalVel() {
		return totalVel;
	}

	public void setTotalVel(double totalVel) {
		this.totalVel = totalVel;
	}

	public double getBullet1Height() {
		return bullet1Height;
	}

	public void setBullet1Height(double bullet1Height) {
		this.bullet1Height = bullet1Height;
	}

	public double getBullet1Width() {
		return bullet1Width;
	}

	public void setBullet1Width(double bullet1Width) {
		this.bullet1Width = bullet1Width;
	}

	public double getBullet2Height() {
		return bullet2Height;
	}

	public void setBullet2Height(double bullet2Height) {
		this.bullet2Height = bullet2Height;
	}

	public double getBullet2Width() {
		return bullet2Width;
	}

	public void setBullet2Width(double bullet2Width) {
		this.bullet2Width = bullet2Width;
	}

}
