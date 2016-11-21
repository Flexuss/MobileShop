package ru.kpfu.itis.dmitry_ivanov;

/**
 * Created by Dmitry on 14.11.2016.
 */
public class Product {

   public int productId;
    public String name;
    public String os;
    public String sim;
    public String weigth;
    public String display;
    public String diagonal;
    public String resolution;
    public String camera;
    public String processor;
    public String ram;
    public String rom;
    public String video;
    public String battery;
    public String cost;
    public String image;

    public Product(){

    }

    public Product(int productId, String name, String os, String sim, String weigth, String display, String diagonal, String resolution, String camera, String processor
            , String ram, String rom, String video, String battery, String cost, String image) {
        this.productId = productId;


        this.name = name;
        this.os = os;

        this.sim = sim;


        this.weigth = weigth;


        this.display = display;


        this.diagonal = diagonal;


        this.resolution = resolution;


        this.camera = camera;


        this.processor = processor;


        this.ram = ram;


        this.rom = rom;


        this.video = video;

        this.battery = battery;


        this.cost = cost;
        this.image=image;
    }
}
