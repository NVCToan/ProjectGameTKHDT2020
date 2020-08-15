package Model;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class CacheDataLoader {
 private static CacheDataLoader  instance;
 

 private String soundFile = "data/sounds.txt";
 private static Hashtable<String, SoundPlayer> sounds;
 private CacheDataLoader() {}
 public static CacheDataLoader getInstance() {
	if(instance==null) {
		instance = new CacheDataLoader();
		
	}
	return instance;
 }



// Load Sound
 public void LoadSounds() throws IOException{
     sounds = new Hashtable<String, SoundPlayer>();
     
     FileReader fr = new FileReader(soundFile);
     BufferedReader br = new BufferedReader(fr);
     
     String line = null;
     
     if(br.readLine()==null) { // no line = "" or something like that
         System.out.println("No data");
         throw new IOException();
     }
     else {
         
         fr = new FileReader(soundFile);
         br = new BufferedReader(fr);
         
         while((line = br.readLine()).equals(""));
         
         int n = Integer.parseInt(line);
         
         for(int i = 0;i < n; i ++){
             
        	 SoundPlayer sound = null;
             while((line = br.readLine()).equals(""));

             String[] str = line.split(" ");
             String name = str[0];
             
             String path = str[1];

             sound = new SoundPlayer(new File(str[1]));
             
             instance.sounds.put(name, sound);
         }
         
     }
     
     br.close();
     
 }

 // GetSounds
 public SoundPlayer getSound(String name){
     return instance.sounds.get(name);
 }

// load data chung
public void loadData() throws IOException{
	LoadSounds();
}
}
