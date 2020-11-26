package buildings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Swing {

    static JFrame jFrame = getJFrame();

    static JPanel jPanel1 = new JPanel();
    static JPanel jPanel2 = new JPanel();
    static JPanel jPanel3 = new JPanel();



    public static void main(String[] args) {

        jPanel1.add(new JButton("Salam1"));
        jPanel2.add(new JButton("Salam2"));
        jPanel3.add(new JButton("Salam3"));

        jFrame.add(jPanel1);
        jFrame.add(jPanel2);
        jFrame.add(jPanel3);



        /*JButton jButton = new JButton("Salam");
        jButton.addActionListener(new OpenBuilding());
        jPanel.add(jButton);*/

        JMenuBar jMenuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        jMenuBar.add(file);

        JMenuItem openDwelling = new JMenuItem("Open Dwelling");
        JMenuItem openOffice = new JMenuItem("Open Office Building");
        JMenuItem openHotel = new JMenuItem("Open Hotel");




        file.add(openDwelling).addActionListener(new OpenBuilding(new DwellingFactory()));
        file.add(openOffice).addActionListener(new OpenBuilding(new OfficeFactory()));
        file.add(openHotel).addActionListener(new OpenBuilding(new HotelFactory()));
        file.addSeparator();
        file.add(new JMenuItem("Exit")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jFrame.setJMenuBar(jMenuBar);
        jFrame.revalidate();
    }

    static class OpenBuilding extends AbstractAction {
        private final BuildingFactory _factory;
        OpenBuilding(BuildingFactory factory){
            _factory = factory;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showOpenDialog(jFrame);
            File file = jFileChooser.getSelectedFile();
            Buildings.setBuildingFactory(_factory);
            FileReader fr = null;
            try {
                fr = new FileReader(file);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            Building b = Buildings.readBuilding(fr);
            System.out.println(b);
        }
    }

    static JFrame getJFrame(){
        JFrame jFrame = new JFrame(){};
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = 1000;
        int height = 700;
        jFrame.setBounds(dimension.width/2 - width/2, dimension.height/2 - height/2, width, height);
        jFrame.setTitle("Salam");
        return jFrame;
    }
}
