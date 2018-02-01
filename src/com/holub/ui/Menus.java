package com.holub.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Example of how to make a menu with raw APIs
 */
public class Menus extends JFrame {
    public Menus() throws HeadlessException {

        JComponent theUniverse = new ProtoUniverse();
        MenuContributor theClock = new ProtoClock();

        JMenuBar menuBar = new JMenuBar();

        theClock.addMenus(menuBar);
        ((MenuContributor) theUniverse).addMenus(menuBar);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });

        menuBar.add(exit);

        menuBar.setVisible(true);
        setJMenuBar(menuBar);

        getContentPane().add(theUniverse);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setSize(200, 200);
        setVisible(true); // show() is deprecated
    }

    public static void main(String[] args) {
        new Menus();
    }


}


interface MenuContributor {
    void addMenus(JMenuBar menuBar);
}

class ProtoClock implements MenuContributor {
    @Override
    public void addMenus(final JMenuBar menuBar) {
        JMenuItem halt = new JMenuItem("Halt");
        JMenuItem slow = new JMenuItem("Slow");
        JMenuItem fast = new JMenuItem("Fast");

        halt.setName("halt");
        slow.setName("slow");
        fast.setName("fast");

        ActionListener handler = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                String name = ((JMenuItem)e.getSource()).getName();
                switch (name.charAt(0)) {
                    case 'h': setClockSpeed(0);break;
                    case 'f': setClockSpeed(500);break;
                    case 's': setClockSpeed(250);break;
                }
            }
        };

        halt.addActionListener(handler);
        slow.addActionListener(handler);
        fast.addActionListener(handler);

        JMenu go = new JMenu("Go");
        go.add(halt);
        go.add(slow);
        go.add(fast);

        menuBar.add(go);

    }

    private void setClockSpeed(int speed) {
        System.out.println("Changing speed to " + speed);
    }
}



class ProtoUniverse extends JPanel implements Cell, MenuContributor {
    @Override
    public void addMenus(final JMenuBar menuBar) {
        JMenuItem clear = new JMenuItem("Clear");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem store = new JMenuItem("Store");


        clear.setName("clear");
        load.setName("load");
        store.setName("store");

        ActionListener handler = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                String name = ((JMenuItem) e.getSource()).getName();

                switch (name.charAt(0)) {
                    case 'c':
                        clearGrid();
                        break;
                    case 'l':
                        loadGrid();
                        break;
                    case 's':
                        storeGrid();
                        break;
                }
            }
        };


        clear.addActionListener(handler);
        load.addActionListener(handler);
        store.addActionListener(handler);

        JMenu grid = new JMenu("Grid");
        grid.add(clear);
        grid.add(load);
        grid.add(store);

        menuBar.add(grid);

    }

    // Stubs:

    private void clearGrid() {
        System.out.println("Clear");

    }

    private void loadGrid() {
        System.out.println("Load");

    }

    private void storeGrid() {
        System.out.println("Store");
    }


}


interface Cell {} // Stub