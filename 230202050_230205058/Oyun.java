import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Oyun {

    private Oyuncu oyuncu;
    private Oyuncu bilgisayar;

    public Oyun() {
        oyuncu = new Oyuncu(1, "Oyuncu", 0);
        bilgisayar = new Oyuncu(2, "Bilgisayar", 0);
    }

    public Oyuncu getOyuncu() {
        return oyuncu;
    }

    public void setOyuncu(Oyuncu oyuncu) {
        this.oyuncu = oyuncu;
    }

    public Oyuncu getBilgisayar() {
        return bilgisayar;
    }

    public void setBilgisayar(Oyuncu bilgisayar) {
        this.bilgisayar = bilgisayar;
    }

    public static int SaldiriHesapla(String oyuncuKarti, String bilgisayarKarti, Oyuncu hedefOyuncu) {
        Ucak ucak = new Ucak(0, "Ucak", 10, 20, "Hava", 10);
        Obus obus = new Obus(0, "Obus", 5, 20, "Kara", 10);
        Firkateyn firkateyn = new Firkateyn(0, "Firkateyn", 5, 25, "Deniz", 10);
        Siha siha = new Siha(0, "Siha", 10, 15, "Hava", 10,10);
        Sida sida = new Sida(0, "Sida", 10, 15, "Deniz", 10,10);
        KFS kfs = new KFS(0, "KFS", 10, 10, "Kara", 10,20);

        String hedefKart = (hedefOyuncu.getOyuncuAdi().equals("Oyuncu")) ? oyuncuKarti : bilgisayarKarti;
        String rakipKart = (hedefOyuncu.getOyuncuAdi().equals("Oyuncu")) ? bilgisayarKarti : oyuncuKarti;
    
        String hedefKartSinif = hedefKart.replaceAll("\\d", ""); 
        String rakipKartSinif = rakipKart.replaceAll("\\d", ""); 

        int hedefVurus = 0;
        if (hedefKartSinif.equals("Ucak")) {
            hedefVurus = ucak.getVurus();
        } else if (hedefKartSinif.equals("Obus")) {
            hedefVurus = obus.getVurus();
        } else if (hedefKartSinif.equals("Firkateyn")) {
            hedefVurus = firkateyn.getVurus();
        } else if (hedefKartSinif.equals("Siha")) {
            hedefVurus = siha.getVurus();
        } else if (hedefKartSinif.equals("Sida")) {
            hedefVurus = sida.getVurus();
        } else if (hedefKartSinif.equals("KFS")) {
            hedefVurus = kfs.getVurus();
        }

        if (hedefKartSinif.equals("Ucak")) {
            if (rakipKartSinif.equals("Obus") || rakipKartSinif.equals("KFS") ) {
                hedefVurus += ucak.getKaraVurusAvantaji();
            }
        } else if (hedefKartSinif.equals("Obus")) {
            if (rakipKartSinif.equals("Firkateyn") || rakipKartSinif.equals("Sida") ) {
                hedefVurus += obus.getDenizVurusAvantaji();
            }
        } else if (hedefKartSinif.equals("Firkateyn")) {
            if (rakipKartSinif.equals("Ucak") || rakipKartSinif.equals("Siha")) {
                hedefVurus += firkateyn.getHavaVurusAvantaji();
            }
        } else if (hedefKartSinif.equals("Siha")) {
            if (rakipKartSinif.equals("Obus") || rakipKartSinif.equals("KFS")) {
                hedefVurus += siha.getKaraVurusAvantaji(); 
            }
            if(rakipKartSinif.equals("Firkateyn") || rakipKartSinif.equals("Sida"))
            {
                hedefVurus += siha.getDenizVurusAvantaji();
            }
        } else if (hedefKartSinif.equals("Sida")) {
            if (rakipKartSinif.equals("Obus") || rakipKartSinif.equals("KFS")) {
                hedefVurus += sida.getKaraVurusAvantaji(); 
            }
            if(rakipKartSinif.equals("Ucak") || rakipKartSinif.equals("Siha"))
            {
                hedefVurus += sida.getHavaVurusAvantaji();
            }
        } else if (hedefKartSinif.equals("KFS")) {
            if (rakipKartSinif.equals("Ucak") || rakipKartSinif.equals("Siha")) {
                hedefVurus += kfs.getHavaVurusAvantaji(); 
            }
            if(rakipKartSinif.equals("Firkateyn") || rakipKartSinif.equals("Sida"))
            {
                hedefVurus += kfs.getDenizVurusAvantaji();
            }
        }

        BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                writer.write(hedefOyuncu.getOyuncuAdi() + " Karti: " + hedefKart + " -> Vurus Degeri: " + hedefVurus+"\n");
                writer.write("Rakip Karti: " + rakipKart+"\n");
                
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        return hedefVurus;
    }
    
    public static Object KartObjesiOlustur(String kartAdi) {

        String kartSinif = kartAdi.replaceAll("\\d", ""); 

        switch (kartSinif) {
            case "Ucak":
                return new Ucak(0, "Ucak", 10, 20, "Hava", 10);
            case "Obus":
                return new Obus(0, "Obus", 5, 20, "Kara", 10);
            case "Firkateyn":
                return new Firkateyn(0, "Firkateyn", 5, 25, "Deniz", 10);
            case "Siha":
                return new Siha(0, "Siha", 10, 15, "Hava", 10, 10);
            case "Sida":
                return new Sida(0, "Sida", 10, 15, "Deniz", 10, 10);
            case "KFS":
                return new KFS(0, "KFS", 10, 10, "Kara", 10, 20);
            default:
                System.out.println("HATA: Bilinmeyen kart türü: " + kartSinif);
                return null;
        }
    }
    
    private static JFrame pencere;
    private JPanel merkezPanel;
    private JPanel bilgisayarKartPaneli;
    private JPanel oyuncuKartPaneli;

    public void gorselBaslat(List<String> bilgisayarKartlar, List<String> oyuncuKartlar, List<JButton> butonlar, List<Integer> oyuncuKullanilmaListesi, List<Integer> seviyePuaniListesi) {
        pencere = new JFrame("Kart Oyunu");
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pencere.setLayout(new BorderLayout());

        pencere.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        pencere.setUndecorated(false); 
        pencere.setVisible(true); 

        int width = pencere.getWidth();
        int height = pencere.getHeight();

        try {
            ImageIcon arkaPlanResmi = new ImageIcon("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\resimler\\arkaplan3.png"); 
            JLabel arkaPlanLabel = new JLabel(new ImageIcon(arkaPlanResmi.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH))); 
            arkaPlanLabel.setLayout(new BorderLayout()); 
            pencere.setContentPane(arkaPlanLabel); 
        } catch (Exception e) {
            System.out.println("Arka plan resmi yüklenemedi: " + e.getMessage());
        }

        bilgisayarKartPaneli = new JPanel();
        bilgisayarKartPaneli.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10)); 
        bilgisayarKartPaneli.setOpaque(false); 
        pencere.add(bilgisayarKartPaneli, BorderLayout.NORTH);

        oyuncuKartPaneli = new JPanel();
        oyuncuKartPaneli.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        oyuncuKartPaneli.setOpaque(false); 
        pencere.add(oyuncuKartPaneli, BorderLayout.SOUTH);

        merkezPanel = new JPanel();
        merkezPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20)); 
        merkezPanel.setOpaque(false); 
        pencere.add(merkezPanel, BorderLayout.CENTER);

        int kartSayisi = Math.max(bilgisayarKartlar.size(), oyuncuKartlar.size()); 
        int kartWidth = 1200 / kartSayisi; 
        kartWidth = Math.max(kartWidth, 100); 
        kartWidth = Math.min(kartWidth, 200); 
        int kartHeight = 250; 

        for (String kart : bilgisayarKartlar) {
            JLabel kapaliKart;
            try {
                ImageIcon kartResmi = new ImageIcon("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\resimler\\bilgisayarkarti.png"); 
                Image scaledImage = kartResmi.getImage().getScaledInstance(kartWidth, kartHeight, Image.SCALE_SMOOTH); 
                kartResmi = new ImageIcon(scaledImage);
                kapaliKart = new JLabel(kartResmi); 
            } catch (Exception e) {
                kapaliKart = new JLabel("Kapali Kart");
                kapaliKart.setOpaque(true);
                kapaliKart.setBackground(Color.GRAY);
            }
            kapaliKart.setPreferredSize(new Dimension(kartWidth, kartHeight)); 
            kapaliKart.setHorizontalAlignment(SwingConstants.CENTER);
            kapaliKart.setVerticalAlignment(SwingConstants.CENTER);
            bilgisayarKartPaneli.add(kapaliKart);
        }

        long sifirSayisi = oyuncuKullanilmaListesi.stream().filter(k -> k == 0).count();
        for (int i = 0; i < oyuncuKartlar.size(); i++) {
            String kart = oyuncuKartlar.get(i);
            int kullanilmaDurumu = oyuncuKullanilmaListesi.get(i); 
            int seviyePuani = seviyePuaniListesi.get(i); 
            JButton kartButonu;
            try {
                String kartBase = kart.replaceAll("\\d+$", ""); 
                ImageIcon kartResmi = new ImageIcon("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\resimler\\" + kartBase + ".png"); 
                Image scaledImage = kartResmi.getImage().getScaledInstance(kartWidth, kartHeight, Image.SCALE_SMOOTH); 
                kartResmi = new ImageIcon(scaledImage);
    
                kartButonu = new JButton(kartResmi); 
            } catch (Exception e) {
                kartButonu = new JButton(kart);
            }
    
            kartButonu.setOpaque(true);
            kartButonu.setPreferredSize(new Dimension(kartWidth, kartHeight)); 
            kartButonu.setHorizontalTextPosition(SwingConstants.CENTER); 
            kartButonu.setVerticalTextPosition(SwingConstants.BOTTOM); 

            JPanel kartPanel = new JPanel();
            kartPanel.setLayout(new BorderLayout());
            JLabel kartLabel = new JLabel(kart, SwingConstants.CENTER); 
            kartLabel.setFont(new Font("Arial", Font.BOLD, 16));
            kartPanel.add(kartLabel, BorderLayout.NORTH);
            kartPanel.add(kartButonu, BorderLayout.CENTER);

            JLabel seviyePuaniLabel = new JLabel("Seviye: " + seviyePuani, SwingConstants.CENTER);
            seviyePuaniLabel.setFont(new Font("Arial", Font.BOLD, 14));
            kartPanel.add(seviyePuaniLabel, BorderLayout.SOUTH); 
    
            if (sifirSayisi == 1 || sifirSayisi == 2) {
                kartButonu.setEnabled(true);
                kartButonu.setBackground(Color.CYAN);

                if (kullanilmaDurumu == 0) {
                    kartButonu.setBorder(BorderFactory.createLineBorder(Color.RED, 5)); 
                } else {
                    kartButonu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
                }
    
                butonlar.add(kartButonu); 
            } else if (kullanilmaDurumu == 0) {
                kartButonu.setEnabled(true);
                kartButonu.setBackground(Color.CYAN);
                kartButonu.setBorder(BorderFactory.createLineBorder(Color.RED, 3)); 
                butonlar.add(kartButonu);
            } else {
                kartButonu.setBackground(Color.LIGHT_GRAY); 
                kartButonu.setEnabled(false);
                kartButonu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
            }
            oyuncuKartPaneli.add(kartPanel); 
        }
        pencere.revalidate(); 
        pencere.repaint();   
    }
    
    public void durumGuncelle(List<String> bilgisayarSecilenKartlar, List<String> oyuncuSecilenKartlar) {

        JFrame frame = new JFrame();
        frame.setSize(0, 0);
        frame.setLocation(200, 400); 
        frame.setUndecorated(true); 
        frame.setVisible(true);

        JFrame yeniPencere = new JFrame("Seçilen Kartlar");
        yeniPencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        yeniPencere.setLayout(new BorderLayout());

        yeniPencere.setExtendedState(JFrame.MAXIMIZED_BOTH);
        yeniPencere.setVisible(true); 

        int width = yeniPencere.getWidth();
        int height = yeniPencere.getHeight();

        try {
            ImageIcon arkaPlanResmi = new ImageIcon("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\resimler\\arkaplankarsilasma.png"); 
            JLabel arkaPlanLabel = new JLabel(new ImageIcon(arkaPlanResmi.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH))); 
            arkaPlanLabel.setLayout(new BorderLayout()); 
            yeniPencere.setContentPane(arkaPlanLabel);
        } catch (Exception e) {
            System.out.println("Arka plan resmi yüklenemedi: " + e.getMessage());
        }

        JPanel bilgisayarPaneli = new JPanel();
        bilgisayarPaneli.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        bilgisayarPaneli.setOpaque(false); 
    
        for (String kart : bilgisayarSecilenKartlar) {
            String kartBase = kart.replaceAll("\\d+$", "");
            JPanel kartPanel = new JPanel();
            kartPanel.setLayout(new BorderLayout());
            kartPanel.setOpaque(false); 
            try {
                ImageIcon kartResmi = new ImageIcon("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\resimler\\" + kartBase + ".png");
                Image scaledImage = kartResmi.getImage().getScaledInstance(200, 350, Image.SCALE_SMOOTH);
                kartResmi = new ImageIcon(scaledImage);
    
                JLabel kartResimLabel = new JLabel(kartResmi);
                kartPanel.add(kartResimLabel, BorderLayout.CENTER); 
            } catch (Exception e) {
                JLabel kartResimLabel = new JLabel("Resim Yok", SwingConstants.CENTER);
                kartPanel.add(kartResimLabel, BorderLayout.CENTER);
            }
    
            JLabel kartIsimLabel = new JLabel(kart, SwingConstants.CENTER);
            kartIsimLabel.setFont(new Font("Arial", Font.BOLD, 18));
            kartIsimLabel.setForeground(Color.BLACK); 
            kartIsimLabel.setOpaque(true);
            kartIsimLabel.setBackground(Color.WHITE); 
            kartPanel.add(kartIsimLabel, BorderLayout.NORTH);
            kartPanel.setPreferredSize(new Dimension(200, 350));
            kartPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            bilgisayarPaneli.add(kartPanel);
        }

        JPanel oyuncuPaneli = new JPanel();
        oyuncuPaneli.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20)); 
        oyuncuPaneli.setOpaque(false); 
    
        for (String kart : oyuncuSecilenKartlar) {
            String kartBase = kart.replaceAll("\\d+$", "");
            JPanel kartPanel = new JPanel();
            kartPanel.setLayout(new BorderLayout());
            kartPanel.setOpaque(false); 
            try {
                ImageIcon kartResmi = new ImageIcon("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\resimler\\" + kartBase + ".png");
                Image scaledImage = kartResmi.getImage().getScaledInstance(200, 350, Image.SCALE_SMOOTH);
                kartResmi = new ImageIcon(scaledImage);
    
                JLabel kartResimLabel = new JLabel(kartResmi);
                kartPanel.add(kartResimLabel, BorderLayout.CENTER);
            } catch (Exception e) {
                JLabel kartResimLabel = new JLabel("Resim Yok", SwingConstants.CENTER);
                kartPanel.add(kartResimLabel, BorderLayout.CENTER);
            }
            JLabel kartIsimLabel = new JLabel(kart, SwingConstants.CENTER);
            kartIsimLabel.setFont(new Font("Arial", Font.BOLD, 18));
            kartIsimLabel.setForeground(Color.BLACK); 
            kartIsimLabel.setOpaque(true);
            kartIsimLabel.setBackground(Color.WHITE); 
            kartPanel.add(kartIsimLabel, BorderLayout.NORTH);
            kartPanel.setPreferredSize(new Dimension(200, 350));
            kartPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            oyuncuPaneli.add(kartPanel);
        }
        JPanel ayiriciPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, getHeight() / 2 - 2, getWidth(), 4); 
            }
        };
        ayiriciPanel.setPreferredSize(new Dimension(0, 10));
        ayiriciPanel.setOpaque(false); 
        yeniPencere.add(bilgisayarPaneli, BorderLayout.NORTH); 
        yeniPencere.add(ayiriciPanel, BorderLayout.CENTER);   
        yeniPencere.add(oyuncuPaneli, BorderLayout.SOUTH);  

        yeniPencere.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
                    yeniPencere.dispose(); 
                }
            }
        });
        yeniPencere.setVisible(true);
        yeniPencere.requestFocus();
        JOptionPane.showMessageDialog(frame, "Devam etmek için ENTER tuşuna basin.");
        frame.dispose();
        yeniPencere.dispose();
    }
    
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt");
            writer.write(""); 
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Oyun oyun = new Oyun();
        List<JButton> butonlar = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        int baslangicSeviyePuani;
        System.out.print("Kartlarin Baslangic seviye puani: ");
        baslangicSeviyePuani = scanner.nextInt();
        oyun.bilgisayar.kartUret(baslangicSeviyePuani);
        oyun.oyuncu.kartUret(baslangicSeviyePuani);
        
        int istisnadurum=0;
        int turSayisi;
        System.out.print("Tur sayisi: ");
        turSayisi = scanner.nextInt();
        BufferedWriter writer = null; 
        try {
            writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
            writer.write("1. TUR"+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        for (int i = 0; i < turSayisi; i++) {
            if (i != 0) {
            oyun.oyuncu.kartUret(oyun.oyuncu.getSkor(), oyun.oyuncu, oyun.bilgisayar,baslangicSeviyePuani);
            oyun.bilgisayar.kartUret(oyun.bilgisayar.getSkor(), oyun.oyuncu, oyun.bilgisayar,baslangicSeviyePuani);
            try {
                writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                writer.write("Eklenen Kartli Liste Oyuncu: " + oyun.oyuncu.getKartListesi()+"\n");
                writer.write("Eklenen Kartli Liste Bilgisayar: " + oyun.bilgisayar.getKartListesi()+"\n");
                writer.write((i+1)+". TUR"+"\n");
                
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            }
            oyun.gorselBaslat(oyun.bilgisayar.getKartListesi(), oyun.oyuncu.getKartListesi(), butonlar,oyun.oyuncu.getKullanilmaListesi(),oyun.oyuncu.getSeviyePuaniListesi());
            oyun.bilgisayar.kartSec();
        if (oyun.oyuncu.kartSec(butonlar, oyun.pencere,oyun.oyuncu) == 1) {
            pencere.dispose();
            oyun.durumGuncelle(oyun.bilgisayar.getSecilenKartlar(),oyun.oyuncu.getSecilenKartlar());
            
        }
            try {
                writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                writer.write("Oyuncu seçilen kartlar: " + oyun.oyuncu.getSecilenKartlar()+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            for (int j = 0; j < 3; j++) {
                String oyuncuKart = oyun.oyuncu.getSecilenKartlar(j); 
                String bilgisayarKart = oyun.bilgisayar.getSecilenKartlar(j);    
                int oyuncuVurus = SaldiriHesapla(oyuncuKart, bilgisayarKart, oyun.oyuncu); 
                int bilgisayarVurus = SaldiriHesapla(oyuncuKart, bilgisayarKart, oyun.bilgisayar);       
                Object oyuncuKartObjesi = KartObjesiOlustur(oyuncuKart);
                Object bilgisayarKartObjesi = KartObjesiOlustur(bilgisayarKart);

                if (oyuncuKartObjesi instanceof Ucak) {
                    ((Ucak) oyuncuKartObjesi).DurumGuncelle(oyuncuKart, bilgisayarKart, bilgisayarVurus, oyun.oyuncu, oyun.bilgisayar, 1);
                } else if (oyuncuKartObjesi instanceof Obus) {
                    ((Obus) oyuncuKartObjesi).DurumGuncelle(oyuncuKart, bilgisayarKart, bilgisayarVurus, oyun.oyuncu, oyun.bilgisayar, 1);
                } else if (oyuncuKartObjesi instanceof Firkateyn) {
                    ((Firkateyn) oyuncuKartObjesi).DurumGuncelle(oyuncuKart, bilgisayarKart, bilgisayarVurus, oyun.oyuncu, oyun.bilgisayar, 1);
                } else if (oyuncuKartObjesi instanceof Siha) {
                    ((Siha) oyuncuKartObjesi).DurumGuncelle(oyuncuKart, bilgisayarKart, bilgisayarVurus, oyun.oyuncu, oyun.bilgisayar, 1);
                } else if (oyuncuKartObjesi instanceof Sida) {
                    ((Sida) oyuncuKartObjesi).DurumGuncelle(oyuncuKart, bilgisayarKart, bilgisayarVurus, oyun.oyuncu, oyun.bilgisayar, 1);
                } else if (oyuncuKartObjesi instanceof KFS) {
                    ((KFS) oyuncuKartObjesi).DurumGuncelle(oyuncuKart, bilgisayarKart, bilgisayarVurus, oyun.oyuncu, oyun.bilgisayar, 1);
                }

                if (bilgisayarKartObjesi instanceof Ucak) {
                    ((Ucak) bilgisayarKartObjesi).DurumGuncelle(bilgisayarKart, oyuncuKart, oyuncuVurus,oyun.oyuncu, oyun.bilgisayar);
                } else if (bilgisayarKartObjesi instanceof Obus) {
                    ((Obus) bilgisayarKartObjesi).DurumGuncelle(bilgisayarKart, oyuncuKart, oyuncuVurus, oyun.oyuncu, oyun.bilgisayar);
                } else if (bilgisayarKartObjesi instanceof Firkateyn) {
                    ((Firkateyn) bilgisayarKartObjesi).DurumGuncelle(bilgisayarKart, oyuncuKart, oyuncuVurus,oyun.oyuncu,oyun.bilgisayar);
                } else if (bilgisayarKartObjesi instanceof Siha) {
                    ((Siha) bilgisayarKartObjesi).DurumGuncelle(bilgisayarKart, oyuncuKart, oyuncuVurus, oyun.oyuncu, oyun.bilgisayar);
                } else if (bilgisayarKartObjesi instanceof Sida) {
                    ((Sida) bilgisayarKartObjesi).DurumGuncelle(bilgisayarKart, oyuncuKart, oyuncuVurus, oyun.oyuncu, oyun.bilgisayar);
                } else if (bilgisayarKartObjesi instanceof KFS) {
                    ((KFS) bilgisayarKartObjesi).DurumGuncelle(bilgisayarKart, oyuncuKart, oyuncuVurus, oyun.oyuncu, oyun.bilgisayar);
                }
                
                int kartIndex = oyun.oyuncu.getDayaniklilikListesi().indexOf(0);
                if (oyun.oyuncu.getDayaniklilikListesi().contains(0)) {
                    
                    int eklenenSeviye = oyun.oyuncu.getSeviyePuaniListesi().get(kartIndex);
                    eklenenSeviye = Math.max(eklenenSeviye, 10); 
                    oyun.bilgisayar.setSkor(oyun.bilgisayar.getSkor() + eklenenSeviye);
                    try {
                        writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                        writer.write("Bilgisayar skoruna " + eklenenSeviye + " puan eklendi."+"\n");
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (writer != null) {
                                writer.close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                int kartIndex2 = oyun.bilgisayar.getDayaniklilikListesi().indexOf(0);
                if (oyun.bilgisayar.getDayaniklilikListesi().contains(0)) {
                    
                    int eklenenSeviye = oyun.bilgisayar.getSeviyePuaniListesi().get(kartIndex2);
                    eklenenSeviye = Math.max(eklenenSeviye, 10); 
                    oyun.oyuncu.setSkor(oyun.oyuncu.getSkor() + eklenenSeviye);
                    try {
                        writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                        writer.write("Oyuncu skoruna " + eklenenSeviye + " puan eklendi."+"\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (writer != null) {
                                writer.close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                if (oyun.bilgisayar.getDayaniklilikListesi().contains(0)) 
                {
                oyun.bilgisayar.getDayaniklilikListesi().remove(kartIndex2);
                oyun.bilgisayar.getSeviyePuaniListesi().remove(kartIndex2);
                oyun.bilgisayar.getKullanilmaListesi().remove(kartIndex2);
                oyun.bilgisayar.getKartListesi().remove(kartIndex2);
                }
                if (oyun.oyuncu.getDayaniklilikListesi().contains(0))
                {
                oyun.oyuncu.getDayaniklilikListesi().remove(kartIndex);
                oyun.oyuncu.getSeviyePuaniListesi().remove(kartIndex);
                oyun.oyuncu.getKullanilmaListesi().remove(kartIndex);
                oyun.oyuncu.getKartListesi().remove(kartIndex);
                }  
            }
            if(istisnadurum == 1)
                {
                    break;
                }
                if(oyun.oyuncu.getKartListesi().size() == 0)
                {
                    try {
                        writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                        writer.write("-------------BILGISAYAR KAZANDI-------------"+"\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (writer != null) {
                                writer.close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                if(oyun.bilgisayar.getKartListesi().size() == 0)
                {
                    try {
                        writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                        writer.write("-------------OYUNCU KAZANDI-------------"+"\n");
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (writer != null) {
                                writer.close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                if(oyun.oyuncu.getKartListesi().size() == 1)
                {
                    oyun.oyuncu.kartUret(oyun.oyuncu.getSkor(),oyun.oyuncu,oyun.bilgisayar,baslangicSeviyePuani);
                    istisnadurum=1;
                }
                if(oyun.bilgisayar.getKartListesi().size() == 1)
                {
                    oyun.bilgisayar.kartUret(oyun.bilgisayar.getSkor(),oyun.oyuncu,oyun.bilgisayar,baslangicSeviyePuani);
                    istisnadurum=1;
                }
            try {
                writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                writer.write("Oyuncu Kartlar Listesi: "+oyun.oyuncu.getKartListesi()+"\n");
                writer.write("Blgisayar Kartlar Listesi: "+oyun.bilgisayar.getKartListesi()+"\n");
                writer.write("Oyuncu Dayaniklilik Listesi: " + oyun.oyuncu.getDayaniklilikListesi()+"\n");
                writer.write("Bilgisayar Dayaniklilik Listesi: " + oyun.bilgisayar.getDayaniklilikListesi()+"\n");
                writer.write("Oyuncu SeviyePuani Listesi: "+oyun.oyuncu.getSeviyePuaniListesi()+"\n");
                writer.write("Bilgisayar SeviyePuani Listesi: "+oyun.bilgisayar.getSeviyePuaniListesi()+"\n");
                writer.write("Oyuncu Skoru: "+oyun.oyuncu.getSkor()+"\n");
                writer.write("Bilgisayar Skoru: "+oyun.bilgisayar.getSkor()+"\n");
                
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
        }
        JFrame pencere = new JFrame("Kart Puanları");
        pencere.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pencere.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        pencere.setLayout(new BorderLayout());

        int bilgisayarKartSayisi = oyun.bilgisayar.getKartListesi().size();
        int oyuncuKartSayisi = oyun.oyuncu.getKartListesi().size();

        JPanel bilgisayarPaneli = new JPanel();
        bilgisayarPaneli.setLayout(new GridLayout(1, bilgisayarKartSayisi, 10, 10)); 
        pencere.add(bilgisayarPaneli, BorderLayout.NORTH);

        JPanel oyuncuPaneli = new JPanel();
        oyuncuPaneli.setLayout(new GridLayout(1, oyuncuKartSayisi, 10, 10));
        pencere.add(oyuncuPaneli, BorderLayout.SOUTH);

int yer = 1;
for (String kart : oyun.oyuncu.getKartListesi()) {
    Object oyuncuKart = KartObjesiOlustur(kart);

    if (oyuncuKart instanceof Ucak) {
        ((Ucak) oyuncuKart).KartPuaniGoster(pencere, yer, oyun.oyuncu.getKartListesi().size(), "Oyuncu",oyun.oyuncu,kart);
    } else if (oyuncuKart instanceof Obus) {
        ((Obus) oyuncuKart).KartPuaniGoster(pencere, yer, oyun.oyuncu.getKartListesi().size(), "Oyuncu",oyun.oyuncu,kart);
    } else if (oyuncuKart instanceof Firkateyn) {
        ((Firkateyn) oyuncuKart).KartPuaniGoster(pencere, yer, oyun.oyuncu.getKartListesi().size(), "Oyuncu",oyun.oyuncu,kart);
    } else if (oyuncuKart instanceof Siha) {
        ((Siha) oyuncuKart).KartPuaniGoster(pencere, yer, oyun.oyuncu.getKartListesi().size(), "Oyuncu",oyun.oyuncu,kart);
    } else if (oyuncuKart instanceof Sida) {
        ((Sida) oyuncuKart).KartPuaniGoster(pencere, yer, oyun.oyuncu.getKartListesi().size(), "Oyuncu",oyun.oyuncu,kart);
    } else if (oyuncuKart instanceof KFS) {
        ((KFS) oyuncuKart).KartPuaniGoster(pencere, yer, oyun.oyuncu.getKartListesi().size(), "Oyuncu",oyun.oyuncu,kart);
    }

    yer++; 
}
int yer2=1;
for(String kart: oyun.bilgisayar.getKartListesi())
        {
            Object bilgisayarKart = KartObjesiOlustur(kart);
            if (bilgisayarKart instanceof Ucak) {
                ((Ucak) bilgisayarKart).KartPuaniGoster(pencere, yer, oyun.bilgisayar.getKartListesi().size(), "Bilgisayar",oyun.bilgisayar,kart);
            } else if (bilgisayarKart instanceof Obus) {
                ((Obus) bilgisayarKart).KartPuaniGoster(pencere, yer, oyun.bilgisayar.getKartListesi().size(), "Bilgisayar",oyun.bilgisayar,kart);
            } else if (bilgisayarKart instanceof Firkateyn) {
                ((Firkateyn) bilgisayarKart).KartPuaniGoster(pencere, yer, oyun.bilgisayar.getKartListesi().size(), "Bilgisayar",oyun.bilgisayar,kart);
            } else if (bilgisayarKart instanceof Siha) {
                ((Siha) bilgisayarKart).KartPuaniGoster(pencere, yer, oyun.bilgisayar.getKartListesi().size(), "Bilgisayar",oyun.bilgisayar,kart);
            } else if (bilgisayarKart instanceof Sida) {
                ((Sida) bilgisayarKart).KartPuaniGoster(pencere, yer, oyun.bilgisayar.getKartListesi().size(), "Bilgisayar",oyun.bilgisayar,kart);
            } else if (bilgisayarKart instanceof KFS) {
                ((KFS) bilgisayarKart).KartPuaniGoster(pencere, yer, oyun.bilgisayar.getKartListesi().size(), "Bilgisayar",oyun.bilgisayar,kart);
            }
        
            yer++;
        }
pencere.setVisible(true);


        oyun.oyuncu.SkorGoster(oyun.oyuncu,oyun.bilgisayar,pencere);
        if(oyun.oyuncu.getSkor()>oyun.bilgisayar.getSkor())
         {
            try {
                writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                writer.write("-------------OYUNCU KAZANDI-------------"+"\n");
                writer.write("Oyuncu Puani: "+oyun.oyuncu.getSkor()+"\n"); 
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
         }
         else if(oyun.oyuncu.getSkor()<oyun.bilgisayar.getSkor())
         {
            try {
                writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                writer.write("-------------BILGISAYAR KAZANDI-------------"+"\n");
                writer.write("Bilgisayar Puani: "+oyun.bilgisayar.getSkor()+"\n");
                
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
         }
         else
         {
            int oyuncuDayaniklilikToplam = oyun.oyuncu.getDayaniklilikListesi().stream().mapToInt(Integer::intValue).sum();
            int bilgisayarDayaniklilikToplam = oyun.bilgisayar.getDayaniklilikListesi().stream().mapToInt(Integer::intValue).sum();
            try {
                writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                writer.write("Oyuncu Dayaniklilik Toplami: "+oyuncuDayaniklilikToplam+"\n");
                writer.write("Bilgisayar Dayaniklilik Toplami: "+bilgisayarDayaniklilikToplam+"\n");           
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            if(bilgisayarDayaniklilikToplam>oyuncuDayaniklilikToplam)
            {
                oyun.bilgisayar.setSkor(oyun.bilgisayar.getSkor()+(bilgisayarDayaniklilikToplam-oyuncuDayaniklilikToplam));
                try {
                    writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                    writer.write("-------------BILGISAYAR KAZANDI-------------"+"\n");
                    writer.write("Bilgisayar Puani: "+oyun.bilgisayar.getSkor()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (writer != null) {
                            writer.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            else
            {
                oyun.oyuncu.setSkor(oyun.oyuncu.getSkor()+(oyuncuDayaniklilikToplam-bilgisayarDayaniklilikToplam));
                try {
                    writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                    writer.write("-------------OYUNCU KAZANDI-------------"+"\n");
                    writer.write("Oyuncu Puani: "+oyun.oyuncu.getSkor()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (writer != null) {
                            writer.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
         }
    }
    
    
}
