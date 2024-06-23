package Game_Crossing_Road.Misc;

import java.util.Random;

public class RandomImages {

    public static String carFromLeft() {
        try {
            switch (new Random().nextInt(8)) {
                case 1:
                    return "res/Images/Cars/cars coming from left/Car_Right_Blue.png";
                case 2:
                    return "res/Images/Cars/cars coming from left/Car_Right_Green.png";
                case 3:
                    return "res/Images/Cars/cars coming from left/Car_Right_Grey.png";
                case 4:
                    return "res/Images/Cars/cars coming from left/Car_Right_Orange.png";
                case 5:
                    return "res/Images/Cars/cars coming from left/Car_Right_Purple.png";
                case 6:
                    return "res/Images/Cars/cars coming from left/Car_Right_Red.png";
                case 7:
                    return "res/Images/Cars/cars coming from left/Car_Right_White.png";
                case 0:
                    return "res/Images/Cars/cars coming from left/Car_Right_Yellow.png";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Error";
    }

    public static String carFromRight() {
        try {
            switch (new Random().nextInt(8)) {
                case 1:
                    return "res/Images/Cars/cars coming from right/Car_Left_Blue.png";
                case 2:
                    return "res/Images/Cars/cars coming from right/Car_Left_Green.png";
                case 3:
                    return "res/Images/Cars/cars coming from right/Car_Left_Grey.png";
                case 4:
                    return "res/Images/Cars/cars coming from right/Car_Left_Orange.png";
                case 5:
                    return "res/Images/Cars/cars coming from right/Car_Left_Purple.png";
                case 6:
                    return "res/Images/Cars/cars coming from right/Car_Left_Red.png";
                case 7:
                    return "res/Images/Cars/cars coming from right/Car_Left_White.png";
                case 0:
                    return "res/Images/Cars/cars coming from right/Car_Left_Yellow.png";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }
    public static String train(){
        try {
            switch (new Random().nextInt(8)) {
                case 0:
                    return "res/Images/trains/Train_Blue.png";
                case 1:
                    return "res/Images/trains/Train_Green.png";
                case 2:
                    return "res/Images/trains/Train_Grey.png";
                case 3:
                    return "res/Images/trains/Train_Orange.png";
                case 4:
                    return "res/Images/trains/Train_Purple.png";
                case 5:
                    return "res/Images/trains/Train_Red.png";
                case 6:
                    return "res/Images/trains/Train_White.png";
                case 7:
                    return "res/Images/trains/Train_Yellow.png";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }
}
