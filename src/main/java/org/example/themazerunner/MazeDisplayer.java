package org.example.themazerunner;

import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.animation.KeyFrame;


class MazeDisplayer
{
	public static  int NUM_OF_FRAMES = 3;
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 750;
	private int RECT_SIZE;
	private ImageView character;
	private ImageView gate;
	private double characterX;
	private double characterY=0;
	private Pane root ;
	private int [][] mazeData ;
	private int currentFrame = 0;
	private Timeline timeline ;


	public Pane getRoot() {
		return root;
	}
	public void setRoot(Pane root) {
		this.root = root;
	}

	public int [][] getMazeData() {
		return mazeData;
	}
	public void setMazeData(int [][] mazeData) {
		this.mazeData = mazeData;
	}

	public MazeDisplayer(Pane root, int [][] mazeData, int RECT_SIZE,int characterX)
	{
		this.characterX = characterX;
		this.RECT_SIZE = RECT_SIZE;
		setRoot(root) ;
		setMazeData(mazeData);
	}
	private void drawCharacter()
	{
		character = new ImageView(new Image("file:E:/code/TheMazeRunner/src/main/java/image/hold1.png"));
		SpriteAnimation animation = new SpriteAnimation(character,
				Duration.millis(1000), 3, 32, 32);
		animation.setCycleCount(javafx.animation.Animation.INDEFINITE);
		animation.play();
		character.setFitWidth(RECT_SIZE);
		character.setFitHeight(RECT_SIZE);
		character.setX(characterX);
		character.setY(characterY);
		root.getChildren().add(character);
	}
	private void lightCharacter(){ // hàm tạo ánh sáng bao quanh nhân vật
		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(Color.rgb(255,255,255,0.4)); // Màu sắc của ánh sáng
		dropShadow.setRadius(500);// Bán kính của ánh sáng
		dropShadow.setSpread(0.95);
		// Áp dụng hiệu ứng ánh sáng cho nhân vật
		character.setEffect(dropShadow);
	}
	private void lightGate(){  // hàm tạo ánh sáng xung quanh cổng
		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(Color.rgb(255,255,255,0.4)); // Màu sắc của ánh sáng
		dropShadow.setRadius(500);// Bán kính của ánh sáng
		dropShadow.setSpread(0.95);
		gate.setEffect(dropShadow);
	}

	private void drawMaze()
	{
		Image background;

		background = new Image(Links.bgJUNGLEMAZE1);
		ImageView backgroundImage = new ImageView(background);
		backgroundImage.setFitWidth(1000);
		backgroundImage.setFitHeight(750);
		root.getChildren().add(backgroundImage);
		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0; j < mazeData[i].length; j++) {
				if (mazeData[i][j] == 0  )
				{
						Image name;
						name = new Image(Links.WALL_PATH);
						ImageView wall = new ImageView(name) ;
						wall.setFitWidth(RECT_SIZE);
						wall.setFitHeight(RECT_SIZE);
						wall.setX(j*RECT_SIZE);
						wall.setY(i*RECT_SIZE);
						root.getChildren().add(wall);
				}
			}
		}
	}
	private void drawGate(){ // vẽ cổng riêng
		for (int i = 0; i < mazeData.length; i++) {
			for (int j = 0; j < mazeData[i].length; j++) {
				if (mazeData[i][j] < 0) {
					Image name;

						name = new Image(Links.GATE_PATH);
						gate = new ImageView(name);
						gate.setFitWidth(RECT_SIZE);
						gate.setFitHeight(RECT_SIZE);
						gate.setX(j * RECT_SIZE);
						gate.setY(i * RECT_SIZE);

						root.getChildren().add(gate);

				}
			}
		}
	}
	private void handleKeyPress(KeyCode code,int SPEED) {
		switch (code) {
			case W:
				moveCharacter(0, -SPEED); // Lên
				updateFrame(0,1,2,3);
				break;
			case S:
				moveCharacter(0, SPEED); // Xuống
				updateFrame(4,5,6,7);
				break;
			case A:
				moveCharacter(-SPEED, 0); // Trái
				updateFrame(8,9,10,11);
				break;
			case D:
				moveCharacter(SPEED, 0); // Phải
				updateFrame(12,13,14,15);
				break;
			default:
				break;
		}

	}
	private void darkMap(Pane root){ // hiệu ứng tối cho map
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.95);// độ đen của map
		for(Node node : root.getChildren()){
			if(node instanceof ImageView){
				ImageView imageView = (ImageView) node;
				imageView.setEffect(colorAdjust);
			}
		}
	}

	private void moveCharacter(double dx, double dy) {
		double newX = characterX + dx;
		double newY = characterY + dy;

		if(check(newX,newY)==1){
			characterX = newX;
			characterY = newY;
			root.getChildren().remove(character);
			character.setX(characterX);
			character.setY(characterY);
			root.getChildren().add(character);
			// them tieng chan
			//footstep.play();
		}
		if (check(newX,newY)==-1){
			showWinMessage();
			Stage currentStage = (Stage) root.getScene().getWindow();
			currentStage.close();
		}
		if (check(newX,newY)==-2){
			showWinMessage();
			Stage currentStage = (Stage) root.getScene().getWindow();
			currentStage.close();
		}
		if (check(newX,newY)==-3){
			showWinMessage();
			Stage currentStage = (Stage) root.getScene().getWindow();
			currentStage.close();
		}
		if (check(newX,newY)==-4){
			showWinMessage();
			Stage currentStage = (Stage) root.getScene().getWindow();
			currentStage.close();
		}
		if (check(newX,newY)==-5){

		}
		if(check(newX,newY)==3){
			characterX = 5*RECT_SIZE;
			characterY = 1*RECT_SIZE;
			root.getChildren().remove(character);
			character.setX(characterX);
			character.setY(characterY);
			root.getChildren().add(character);
		}
		if(check(newX,newY)==4){
			characterX = 22*RECT_SIZE;
			characterY = 1*RECT_SIZE;
			root.getChildren().remove(character);
			character.setX(characterX);
			character.setY(characterY);
			root.getChildren().add(character);
		}

	}

	private int check(double characterX , double characterY) { // hàm xử lý va chạm
		int top = (int) ((characterY+10)/ RECT_SIZE);
		int left = (int) ((characterX+10)/ RECT_SIZE);
		int bottom = (int) (((characterY-10)+ RECT_SIZE) / RECT_SIZE);
		int right = (int) (((characterX-10) + RECT_SIZE) / RECT_SIZE);
		int top1 = (int) ((characterY)/ RECT_SIZE);
		int left1 = (int) ((characterX)/ RECT_SIZE);
		int bottom1 = (int) (((characterY)+ RECT_SIZE) / RECT_SIZE);
		int right1 = (int) (((characterX) + RECT_SIZE) / RECT_SIZE);

		// Kiểm tra giới hạn mảng
		if (top < 0 || top >= mazeData.length || left < 0 || left >= mazeData[0].length ||
				bottom < 0 || bottom >= mazeData.length || right < 0 || right >= mazeData[0].length) {
			return 0;
		}


		else if(mazeData[top][left] == -1 || mazeData[top][right] == -1 || mazeData[bottom][left] == -1 || mazeData[bottom][right] == -1) return -1;// cổng map1
		else if(mazeData[top][left] == -2 || mazeData[top][right] == -2 || mazeData[bottom][left] == -2 || mazeData[bottom][right] == -2) return -2;// cổng map2
		else if(mazeData[top][left] == -3 || mazeData[top][right] == -3 || mazeData[bottom][left] == -3 || mazeData[bottom][right] == -3) return -3;// cổng map3
		else if(mazeData[top][left] == -4 || mazeData[top][right] == -4 || mazeData[bottom][left] == -4 || mazeData[bottom][right] == -4) return -4;// cổng map4
		else if(mazeData[top][left] == -5 || mazeData[top][right] == -5 || mazeData[bottom][left] == -5 || mazeData[bottom][right] == -5) return -5; // cổng map5
		else if(mazeData[top1][left1] == 3 || mazeData[top1][right1] == 3 || mazeData[bottom1][left1] == 3 || mazeData[bottom1][right1] == 3) return 3;
		else if(mazeData[top1][left1] == 4 || mazeData[top1][right1] == 4 || mazeData[bottom1][left1] == 4 || mazeData[bottom1][right1] == 4) return 4;
		else if(mazeData[top][left] == 1 && mazeData[top][right] == 1 && mazeData[bottom][left] == 1 && mazeData[bottom][right] == 1) return 1; // đường đi
		return 0;

	}
	public void updateFrame(int... frameIndices) {
		currentFrame = (currentFrame + 1) % frameIndices.length;
		int frameIndex = frameIndices[currentFrame];
		//String filePath = Links.FOOTSTEP_PATH;
		//Media soundFootstep = new Media(new File(filePath).toURI().toString());
		//MediaPlayer footstep = new MediaPlayer(soundFootstep);

		// Tạo đường dẫn tới tập tin ảnh mới dựa trên hành động
		String imagePath = "";
		switch (frameIndex) {
			case 0: // Di chuyển lên
				imagePath = Links.UP1_PATH;
				//footstep.play();
				break;
			case 4: // Di chuyển xuống
				imagePath = Links.DOWN1_PATH;
				//footstep.play();
				break;
			case 8: // Di chuyển qua trái
				imagePath = Links.LEFT1_PATH;
				//footstep.play();
				break;
			case 12: // Di chuyển qua phải
				imagePath = Links.RIGHT1_PATH;
				//footstep.play();
				break;
			case 16:
				imagePath = Links.HOLD1_PATH;
				break;
			default:
				break;
		}
		timeline.playFromStart();

		// Nếu đường dẫn hợp lệ, cập nhật hình ảnh của characterImageView
		if (!imagePath.isEmpty()) {

				Image spriteSheet = new Image(imagePath);
				character.setImage(spriteSheet);

		}
	}

	public void showWinMessage() {
		// Tạo một Stage mới cho cửa sổ thông báo tùy chỉnh
		Stage alertStage = new Stage();
		alertStage.initStyle(StageStyle.UNDECORATED);

		// Tạo thanh tiêu đề tùy chỉnh
		HBox titleBar = new HBox();
		titleBar.setStyle("-fx-background-color: #2C3E50; -fx-padding: 5px;");
		titleBar.setAlignment(Pos.CENTER_RIGHT);

		// Tạo nội dung thông báo
		Label messageLabel = new Label();
		Button okButton = new Button();
		Image okImage = new Image(Links.OKBUTTON_PATH);
		ImageView okImageView = new ImageView(okImage);
		okImageView.setFitWidth(100);  // Điều chỉnh kích thước hình ảnh nếu cần
		okImageView.setFitHeight(50);
		okButton.setGraphic(okImageView);

		VBox contentLayout = new VBox(110, messageLabel, okButton);
		contentLayout.setAlignment(Pos.CENTER);
		okButton.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-padding: 0;");
		okButton.setOnAction(e -> alertStage.close());
		contentLayout.setPadding(new Insets(20));

		// Tạo hình ảnh nền và thiết lập nền cho ô thông báo
		Image backgroundImage = new Image(Links.CONGRAT);
		BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
		Background background = new Background(backgroundImg);
		contentLayout.setBackground(background);

		// Tạo bố cục chính và thêm các phần tử vào đó
		BorderPane root = new BorderPane();
		root.setTop(titleBar);
		root.setCenter(contentLayout);

		Scene scene = new Scene(root, 400, 200);
		alertStage.setScene(scene);

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		alertStage.setX((screenBounds.getWidth() - 300) / 2);  // Căn giữa theo chiều ngang
		alertStage.setY((screenBounds.getHeight() - 200) / 2);

		alertStage.showAndWait();
	}
	public void showWinAll(){

	}

	public Scene getSceneMaze1(int x, int y) {
		drawMaze() ;
		drawGate();
		drawCharacter() ;
		Scene scene = new Scene(root, x, y);
		scene.setOnKeyPressed(e -> handleKeyPress(e.getCode(),7));
		timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
			updateFrame(16);
		}));
		return scene ;
	}
	public Scene getSceneMaze2(int x, int y)  // x, y = toa do cua Scene
	{
		drawMaze() ;
		drawGate();
		drawCharacter() ;
		Scene scene = new Scene(root, x, y);
		scene.setOnKeyPressed(e -> handleKeyPress(e.getCode(),7));
		timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
			updateFrame(16);
		}));
		return scene ;
	}
	public Scene getSceneMaze3(int x, int y) // x, y = toa do cua Scene
	{
		drawMaze() ;
		drawGate();
		drawCharacter() ;
		Scene scene = new Scene(root, x, y);
		scene.setOnKeyPressed(e -> handleKeyPress(e.getCode(),5));
		timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
			updateFrame(16);
		}));
		return scene ;
	}
	public Scene getSceneMaze4(int x, int y) // x, y = toa do cua Scene
	{
		drawMaze() ;
		drawGate();
		darkMap(root);
		drawCharacter() ;
		lightCharacter();
		lightGate();
		Scene scene = new Scene(root, x, y);
		scene.setOnKeyPressed(e -> handleKeyPress(e.getCode(),5));
		timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
			updateFrame(16);
		}));
		return scene ;
	}
	public Scene getSceneMaze5(int x, int y) // x, y = toa do cua Scene
	{
		drawMaze() ;
		drawGate();
		darkMap(root);
		drawCharacter() ;
		lightCharacter();
		lightGate();
		Scene scene = new Scene(root, x, y);
		scene.setOnKeyPressed(e -> handleKeyPress(e.getCode(),5));
		timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
			updateFrame(16);
		}));
		return scene ;
	}
}