package com.wakeel.moviesapp.data;

public class URLs {
    public static String BASE_URL = "https://api.themoviedb.org";
    public static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500";
    
    public static String getImagePath(String imagePath) {
        return BASE_IMAGE_URL + imagePath;
    }
}
