package org.example.themazerunner.Maze;

import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

public class Character extends Pane {
    ImageView imageView;
    private double x; // Tọa độ x của nhân vật
    private double y; // Tọa độ y của nhân vật
    int count = 3;
    int columns = 3;
    int offsetX = 0;
    int offsetY = 0;
    int width = 32;
    int height = 32;
    private Rectangle visionBox;
    private static final double VISION_WIDTH = 50; // Width of the vision box
    private static final double VISION_HEIGHT = 50;
    MazeDrawer mazeDrawer; // Thêm biến tham chiếu đến MazeDrawer
    private Rectangle hitbox;
    SpriteAnimation1 animation;

    public Character(ImageView imageView, MazeDrawer mazeDrawer) {
        this.mazeDrawer = mazeDrawer; // Set the mazeDrawer
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation1(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
        hitbox = new Rectangle(this.x,this.y,25,25);
        updateHitbox();
        this.x = 64; // Khởi tạo tọa độ x
        this.y = 64; // Khởi tạo tọa độ y
        setTranslateX(this.x);
        setTranslateY(this.y);
        // Initialize the vision box
        visionBox = new Rectangle (VISION_WIDTH, VISION_HEIGHT);
        updateVisionBox();

    }


    // Phương thức getter cho tọa độ x
    public double getX() {
        return this.x;
    }

    // Phương thức getter cho tọa độ y
    public double getY() {
        return this.y;
    }

    // Phương thức setter cho tọa độ x
    public void setX(double x) {
        this.x = x;
    }

    // Phương thức setter cho tọa độ y
    public void setY(double y) {
        this.y = y;
    }


    public void moveX(int dx) {
        double newX = this.x + dx;
        if (willCollideWithWall(newX, this.y, hitbox.getWidth(), hitbox.getHeight())==1) {
            this.x = newX;
            setTranslateX(this.x);
            updateHitbox() ;
            updateVisionBox();
            //hitbox.setX(this.x);
        }
    }

    public void moveY(int dy) {
        double newY = this.y + dy;
        if (willCollideWithWall(this.x, newY, hitbox.getWidth(), hitbox.getHeight())==1) {
            this.y = newY;
            setTranslateY(this.y);
            updateHitbox() ;
            updateVisionBox();
            //hitbox.setY(this.y);
        }
    }
    public void updateHitbox() {
        double centerX = getTranslateX() + width / 2;
        double centerY = getTranslateY() + height / 2;
        hitbox.setX(centerX - (25) / 2);
        hitbox.setY(centerY - (25) / 2);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void updateVisionBox() {
        double centerX = getTranslateX() + width / 2;
        double centerY = getTranslateY() + height / 2;
        visionBox.setX(centerX - VISION_WIDTH / 2);
        visionBox.setY(centerY - VISION_HEIGHT / 2);
    }

    public Rectangle getVisionBox() {
        return visionBox;
    }

    protected int willCollideWithWall(double x, double y, double width, double height) {
        // Giảm kích thước của hitbox nhân vật và dịch chuyển vị trí
        double smallerWidth = width * 0.8; // Giảm chiều rộng của hitbox nhân vật
        double smallerHeight = height * 0.8; // Giảm chiều cao của hitbox nhân vật
        double xOffset = (width - smallerWidth) / 2; // Dịch chuyển hitbox theo trục X
        double yOffset = (height - smallerHeight) / 2 ; // Dịch chuyển hitbox theo trục Y

        // Tạo hình chữ nhật đại diện cho vị trí mới và kích thước mới của nhân vật
        Rectangle futureCharacter = new Rectangle(x + xOffset, y + yOffset, smallerWidth, smallerHeight);

        // Kiểm tra va chạm với từng tường
        for (Block wall : Main2.walls) {
            if (futureCharacter.getBoundsInParent().intersects(wall.getwall().getBoundsInParent())) {
                return 0; // Nếu có va chạm, ngăn nhân vật di chuyển
            }
        }
        return 1; // Nếu không có va chạm, cho phép nhân vật di chuyển
    }

}