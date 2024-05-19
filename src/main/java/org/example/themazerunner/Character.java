package org.example.themazerunner;


import javafx.geometry.Rectangle2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Character extends Pane {
    ImageView imageView;  
    private double x ; // Tọa độ x của nhân vật
    private double y ; // Tọa độ y của nhân vật
    int count = 3;
    int columns = 3;
    int offsetX = 0;
    int offsetY = 64;
    int width = 64;
    int height = 64;

    MazeDrawer mazeDrawer; // Thêm biến tham chiếu đến MazeDrawer

    SpriteAnimation1 animation;

    public Character(ImageView imageView, MazeDrawer mazeDrawer) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation1(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
        this.x = 32; // Khởi tạo tọa độ x
        this.y = 32; // Khởi tạo tọa độ y
        this.mazeDrawer = mazeDrawer; // Set the mazeDrawer
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(255,0,0,0)); // Màu sắc của ánh sáng
        dropShadow.setRadius(650);// Bán kính của ánh sáng
        dropShadow.setSpread(0.95);
        // Áp dụng hiệu ứng ánh sáng cho nhân vật
        imageView.setEffect(dropShadow);
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

    public void moveX(int x) {
        boolean right = x > 0 ? true : false;
        for(int i = 0; i < Math.abs(x); i++) {
            if(right) {
                this.setTranslateX(this.getTranslateX() + 1);
                setX(getTranslateX());
            }
                
            else {
                this.setTranslateX(this.getTranslateX() - 1);
                setX(getTranslateX());
            }
              
        }
    }

    public void moveY(int y) {
        boolean down = y > 0 ? true : false;
        for(int i = 0; i < Math.abs(y); i++) {
            if(down){
                this.setTranslateY(this.getTranslateY() + 1);
                setY(getTranslateY());
            } 
                
            else {
                this.setTranslateY(this.getTranslateY() -1);
                setY(getTranslateY());

            }
                
        }
    }

    public Boolean isWallCollision(double x, double y) {
        // Kiểm tra xem nhân vật có chạm vào tường không
        int[][] mazeData = mazeDrawer.getMazeData();
        double cellSizeWidth = mazeDrawer.getCellSizeWidth();
        double cellSizeHeight = mazeDrawer.getCellSizeHeight();
    
        // Làm tròn tọa độ x và y đến số nguyên lớn nhất
        int column = ((int) Math.floor((x) / cellSizeWidth));
        int row = ((int) Math.floor((y )/ cellSizeHeight));

        return mazeData[row][column] == 0;
        /*//int column1 = ((int) Math.ceil((x) / cellSizeWidth));
        //int row1 = ((int) Math.ceil((y )/ cellSizeHeight));
    
        // Kiểm tra xem ô mê cung có là tường không
        return mazeData[row][column] == 0 ;*/
        /*int top = (int) ((y)/ 32);
        int left = (int) (x/32);
        int bottom = (int) (((y-5)+ 32) / 32);
        int right = (int) (((x-5) + 32) / 32);

        // Kiểm tra giới hạn mảng
        if (top < 0 || top >= mazeData.length || left < 0 || left >= mazeData[0].length ||
                bottom < 0 || bottom >= mazeData.length || right < 0 || right >= mazeData[0].length) {
            return 0;
        }

        if(mazeData[top][left] == 1 && mazeData[top][right] == 1 && mazeData[bottom][left] == 1 && mazeData[bottom][right] == 1) return 1; // đường đi
        if(mazeData[top][left] == -1 || mazeData[top][right] == -1 || mazeData[bottom][left] == -1 || mazeData[bottom][right] == -1) return -1;// cổng map1
        if(mazeData[top][left] == -2 || mazeData[top][right] == -2 || mazeData[bottom][left] == -2 || mazeData[bottom][right] == -2) return -2;// cổng map2
        if(mazeData[top][left] == -3 || mazeData[top][right] == -3 || mazeData[bottom][left] == -3 || mazeData[bottom][right] == -3) return -3;// cổng map3
        if(mazeData[top][left] == -4 || mazeData[top][right] == -4 || mazeData[bottom][left] == -4 || mazeData[bottom][right] == -4) return -4;// cổng map4
        if(mazeData[top][left] == -5 || mazeData[top][right] == -5 || mazeData[bottom][left] == -5 || mazeData[bottom][right] == -5) return -5; // cổng map5
        if(mazeData[bottom][left]==3) return 3;
        return 0;*/
    }
}
