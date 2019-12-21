package save;

import model.BattleNavaleGame;
import model.century_factory.boats.Boat;
import model.global.Orientation;
import model.global.Position;

import java.io.*;
import java.util.ArrayList;

public class FileManager implements Serializable {

    private String dataH;
    private String dataM;
    private BattleNavaleGame game;

    public FileManager(BattleNavaleGame game) {
        this.game = game;
    }

    /**
     * Methode to load saved data from a the save file
     * then setting the game for the player
     * @throws IOException
     */
    public void load() throws IOException {

        InputStream file = FileManager.class.getClassLoader().getResourceAsStream("gameSaves.txt");
        InputStreamReader lecture = new InputStreamReader(file);
        BufferedReader buff = new BufferedReader(lecture);
        String line;
        Boat b;
        String[] boatsNumbers;
        String[] boatsNumbers1;
        String[] info;
        int boatSize, X, Y, boatHealth;
        int initX = 0, initY = 0;
        Orientation orientation;
        ArrayList<Position> list = new ArrayList<>();

        if ((line = buff.readLine()) != null){
            //Loading Humaine player data
            boatsNumbers = line.split(",");
             for(int i = 0; i<Integer.valueOf(boatsNumbers[1]);i++){
                 list = new ArrayList<>();
                 //orientation
                line = buff.readLine();
                info = line.split(",");
                 //System.out.println(info[0]);
                 if (info[0].equals("1"))
                     orientation = Orientation.HORIZONTAL;
                 else
                     orientation = Orientation.VERTICAL;
                //boatHealth
                boatHealth = Integer.valueOf(info[1]);
                //boatSize
                boatSize = Integer.valueOf(info[2]);
                //boatsNumbers1
                line = buff.readLine();
                boatsNumbers1 = line.split(",");
                for (int j=0 ; j < Integer.valueOf(boatsNumbers1[1]); j++){
                    line = buff.readLine();
                    info = line.split("/");
                    if (j == 0 ){
                        initX = Integer.valueOf(info[0]);
                        initY = Integer.valueOf(info[1]);
                    }
                    X = Integer.valueOf(info[0]);
                    Y = Integer.valueOf(info[1]);
                    list.add(new Position(X,Y));
                }
                b = new Boat(boatSize,boatHealth, initX,initY);
                b.setOrientation(orientation);
                b.setCases(list);
                game.getHumanPlayer().getBoard().addBoatToList(b);
            }
            //setting the score and the missedshot
            line = buff.readLine();
            info = line.split(":");
            int score = Integer.valueOf(info[1]);
            info = line.split(":");
            int missedShot = Integer.valueOf(info[1]);
            game.getHumanPlayer().setScoreHits(score);
            game.getHumanPlayer().setMissedShots(missedShot);

            //////////////////////////////////////////////////////////
            line = buff.readLine();
            //Loading Machine pLayer data
            boatsNumbers1 = line.split(",");
            for(int i = 0; i<Integer.valueOf(boatsNumbers1[1]);i++){
                list = new ArrayList<>();
                line = buff.readLine();
                info = line.split(",");
                if (info[0].equals(1))
                    orientation = Orientation.HORIZONTAL;
                else
                    orientation = Orientation.VERTICAL;
                boatHealth = Integer.valueOf(info[1]);
                boatSize = Integer.valueOf(info[2]);

                line = buff.readLine();
                boatsNumbers = line.split(",");
                for (int j=0 ; j < Integer.valueOf(boatsNumbers[1]); j++){
                    line = buff.readLine();
                    info = line.split("/");
                    if (j == 0 ){
                         initX = Integer.valueOf(info[0]);
                         initY = Integer.valueOf(info[1]);
                    }
                    X = Integer.valueOf(info[0]);
                    Y = Integer.valueOf(info[1]);
                    list.add(new Position(X,Y));
                }
                b = new Boat(boatSize,boatHealth, initX, initY);
                b.setOrientation(orientation);
                b.setCases(list);
                game.getMachinePlayer().getBoard().addBoatToList(b);
            }

        }

        //
        buff.close();
        lecture.close();
        file.close();
    }

    /**
     * Methode to save the gam data
     * Players (Machine and humaine)
     * boats positions, sizes, scores
     */
    public void save (){
        //Getting the data
        this.dataH = game.getHumanPlayer().getData().toString();
        this.dataM = game.getMachinePlayer().getData().toString();

        //Writing the data on the saveFile
        try {
            FileOutputStream configStream = new FileOutputStream("out/production/BatailleNavale_DP2019/gameSaves.txt");
            OutputStream f = configStream;
            OutputStreamWriter write = new OutputStreamWriter(f);
            BufferedWriter b = new BufferedWriter(write);
            System.out.println(dataH);
            b.write(dataH);
            //System.out.println(dataH);
            b.write(dataM);
           // System.out.println(dataM);
            b.close();
            write.close();
            f.close();

        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

    }

}
