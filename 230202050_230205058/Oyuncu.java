import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*; 
import java.awt.*;    
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Oyuncu {

    private int oyuncuID;
    private String oyuncuAdi;
    private int skor;
    private List<String> kartListesi; 
    private List<Integer> dayaniklilikListesi;
    private List<Integer> seviyePuaniListesi;
    private List<Integer> kullanilmaListesi;
    private List<String> secilenKartlar; 

    public Oyuncu() {
        this.oyuncuID = 0;
        this.oyuncuAdi = "Bilinmeyen";
        this.skor = 0;
        this.kartListesi = new ArrayList<>();
        this.dayaniklilikListesi = new ArrayList<>();
        this.seviyePuaniListesi = new ArrayList<>();
        this.kullanilmaListesi = new ArrayList<>();
        this.secilenKartlar = new ArrayList<>();
    }

    public Oyuncu(int oyuncuID, String oyuncuAdi, int skor) {
        this.oyuncuID = oyuncuID;
        this.oyuncuAdi = oyuncuAdi;
        this.skor = skor;
        this.kartListesi = new ArrayList<>();
        this.dayaniklilikListesi = new ArrayList<>();
        this.seviyePuaniListesi = new ArrayList<>();
        this.kullanilmaListesi = new ArrayList<>();
        this.secilenKartlar = new ArrayList<>();
    }

    public int getOyuncuID() {
        return oyuncuID;
    }

    public void setOyuncuID(int oyuncuID) {
        this.oyuncuID = oyuncuID;
    }

    public String getOyuncuAdi() {
        return oyuncuAdi;
    }

    public void setOyuncuAdi(String oyuncuAdi) {
        this.oyuncuAdi = oyuncuAdi;
    }

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

    public List<String> getKartListesi() {
        return kartListesi;
    }

    public void setKartListesi(List<String> kartListesi) {
        this.kartListesi = kartListesi;
    }
    public List<Integer> getDayaniklilikListesi() {
        return dayaniklilikListesi;
    }   
    public void setDayaniklilikListesi(List<Integer> dayaniklilikListesi) {
        this.dayaniklilikListesi = dayaniklilikListesi;
    }

    public List<Integer> getSeviyePuaniListesi() {
        return seviyePuaniListesi;
    }
    public void setSeviyePuaniListesi(List<Integer> seviyePuaniListesi) {
        this.seviyePuaniListesi = seviyePuaniListesi;
    }
    public List<Integer> getKullanilmaListesi() {
        return kullanilmaListesi;
    }
    public void setKullanilmaListesi(List<Integer> kullanilmaListesi) {
        this.kullanilmaListesi = kullanilmaListesi;
    }
    public List<String> getSecilenKartlar() {
        return secilenKartlar;
    }
    public String getSecilenKartlar(int index) {
        return secilenKartlar.get(index);
    }
    public void setSecilenKartlar(List<String> secilenKartlar) {
        this.secilenKartlar = secilenKartlar;
    }

    
    public void kartUret(int baslangicSeviyePuani) {
        String[] kartHavuzu = {"Ucak", "Obus", "Firkateyn"}; 
        int[] baslangicDayaniklilik = {20, 20, 25}; 
        Random rastgele = new Random();
        int[] kartSayaclari = new int[kartHavuzu.length]; 

        for (int i = 0; i < 6; i++) {
            int rastgeleIndex = rastgele.nextInt(kartHavuzu.length); 
            kartSayaclari[rastgeleIndex]++; 
            String kart = kartHavuzu[rastgeleIndex] + kartSayaclari[rastgeleIndex]; 
            kartListesi.add(kart); 
            dayaniklilikListesi.add(baslangicDayaniklilik[rastgeleIndex]); 
            seviyePuaniListesi.add(baslangicSeviyePuani); 
            kullanilmaListesi.add(0);
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
            writer.write(oyuncuAdi + " icin olusturulan kartlar: " + kartListesi+"\n");
            writer.write("Baslangic Dayaniklilik: " + dayaniklilikListesi+"\n");
            writer.write("Baslangic Seviye Puanlari: " + seviyePuaniListesi+"\n");
            writer.write("Kullanilma Listesi: " + kullanilmaListesi+"\n");
            
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

    public void kartUret(int skor,Oyuncu oyuncu,Oyuncu bilgisayar,int baslangicSeviyePuani) {
        String[] kartHavuzu;
        int[] baslangicDayaniklilik;
    
        if (skor < 20) {
            kartHavuzu = new String[]{"Ucak", "Obus", "Firkateyn"};
            baslangicDayaniklilik = new int[]{20, 20, 25};
        } else {
            kartHavuzu = new String[]{"Ucak", "Obus", "Firkateyn", "Siha", "Sida", "KFS"};
            baslangicDayaniklilik = new int[]{20, 20, 25, 15, 15, 10};
        }
    
        Random rastgele = new Random();
        int rastgeleIndex = rastgele.nextInt(kartHavuzu.length); 
    
        String kartTuru = kartHavuzu[rastgeleIndex];
    
        long mevcutKartSayisi = kartListesi.stream()
                .filter(kart -> kart.startsWith(kartTuru))
                .count();

        String yeniKart = kartTuru + (mevcutKartSayisi + 1);

        kartListesi.add(yeniKart);
        dayaniklilikListesi.add(baslangicDayaniklilik[rastgeleIndex]); 
        seviyePuaniListesi.add(baslangicSeviyePuani); 
        kullanilmaListesi.add(0); 

        BufferedWriter writer = null; 
            try {
                writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                writer.write("Yeni Kart Üretildi: " + yeniKart+"\n");
                writer.write("Mevcut Kartlar: " + kartListesi+"\n");
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
    
    public void SkorGoster(Oyuncu oyuncu, Oyuncu bilgisayar, JFrame pencere) {
        JFrame frame = new JFrame();
        frame.setSize(0, 0);
        frame.setLocation(750, 400); 
        frame.setUndecorated(true); 
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, "Devam etmek için ENTER tuşuna basin.");
        frame.dispose();
        pencere.dispose();
    
        JFrame skorPenceresi = new JFrame("Skor Tablosu");
        skorPenceresi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        skorPenceresi.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        skorPenceresi.setLayout(new BorderLayout());
        skorPenceresi.setVisible(true); 
        int width = skorPenceresi.getWidth();
        int height = skorPenceresi.getHeight();

        try {
            ImageIcon arkaPlanResmi = new ImageIcon("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\resimler\\arkaplanson.png"); // Arka plan resmi
            JLabel arkaPlanLabel = new JLabel(new ImageIcon(arkaPlanResmi.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH))); // Dinamik ölçeklendirme
            arkaPlanLabel.setLayout(new BorderLayout()); 
            skorPenceresi.setContentPane(arkaPlanLabel); 
        } catch (Exception e) {
            System.out.println("Arka plan resmi yüklenemedi: " + e.getMessage());
        }

        JPanel anaPanel = new JPanel();
        anaPanel.setLayout(new GridLayout(2, 1));
        anaPanel.setOpaque(false); 
        skorPenceresi.add(anaPanel, BorderLayout.CENTER);

        JPanel skorPaneli = new JPanel();
        skorPaneli.setLayout(new GridLayout(1, 2, 50, 50)); 
        skorPaneli.setOpaque(false);
        anaPanel.add(skorPaneli);

        JPanel bilgisayarPanel = new JPanel();
        bilgisayarPanel.setLayout(new BorderLayout());
        bilgisayarPanel.setOpaque(false);
        JLabel bilgisayarLabel = new JLabel("BİLGİSAYAR SKORU", SwingConstants.CENTER);
        bilgisayarLabel.setFont(new Font("Arial", Font.BOLD, 48)); 
        bilgisayarLabel.setForeground(Color.BLACK);
        JLabel bilgisayarSkorLabel = new JLabel(String.valueOf(bilgisayar.getSkor()), SwingConstants.CENTER);
        bilgisayarSkorLabel.setFont(new Font("Arial", Font.BOLD, 72));
        bilgisayarSkorLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0)); 
        bilgisayarSkorLabel.setForeground(Color.BLUE);
    
        bilgisayarPanel.add(bilgisayarLabel, BorderLayout.NORTH);
        bilgisayarPanel.add(bilgisayarSkorLabel, BorderLayout.CENTER);
        skorPaneli.add(bilgisayarPanel);

        JPanel oyuncuPanel = new JPanel();
        oyuncuPanel.setLayout(new BorderLayout());
        oyuncuPanel.setOpaque(false); 
        JLabel oyuncuLabel = new JLabel("OYUNCU SKORU", SwingConstants.CENTER);
        oyuncuLabel.setFont(new Font("Arial", Font.BOLD, 48)); 
        oyuncuLabel.setForeground(Color.BLACK);
        JLabel oyuncuSkorLabel = new JLabel(String.valueOf(oyuncu.getSkor()), SwingConstants.CENTER);
        oyuncuSkorLabel.setFont(new Font("Arial", Font.BOLD, 72));
        oyuncuSkorLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        oyuncuSkorLabel.setForeground(Color.BLUE);
    
        oyuncuPanel.add(oyuncuLabel, BorderLayout.NORTH);
        oyuncuPanel.add(oyuncuSkorLabel, BorderLayout.CENTER);
        skorPaneli.add(oyuncuPanel);

        JPanel kazananPanel = new JPanel();
        kazananPanel.setLayout(new BorderLayout());
        kazananPanel.setOpaque(false); 
        JLabel kazananLabel = new JLabel("", SwingConstants.CENTER);
        kazananLabel.setFont(new Font("Arial", Font.BOLD, 48));
    
        JLabel yeniSkorLabel = new JLabel("", SwingConstants.CENTER);
        yeniSkorLabel.setFont(new Font("Arial", Font.PLAIN, 36)); 

        if (bilgisayar.getSkor() > oyuncu.getSkor()) {
            kazananLabel.setText("BİLGİSAYAR KAZANDI");
            kazananLabel.setForeground(Color.BLACK);
        } else if (oyuncu.getSkor() > bilgisayar.getSkor()) {
            kazananLabel.setText("OYUNCU KAZANDI");
            kazananLabel.setForeground(Color.BLACK);
        } else {
            int oyuncuDayaniklilik = oyuncu.getDayaniklilikListesi().stream().mapToInt(Integer::intValue).sum();
            int bilgisayarDayaniklilik = bilgisayar.getDayaniklilikListesi().stream().mapToInt(Integer::intValue).sum();
    
            if (oyuncuDayaniklilik > bilgisayarDayaniklilik) {
                int fark = oyuncuDayaniklilik - bilgisayarDayaniklilik;
                oyuncu.setSkor(oyuncu.getSkor() + fark);
                kazananLabel.setText("OYUNCU KAZANDI");
                kazananLabel.setForeground(Color.BLACK);
                yeniSkorLabel.setText("Yeni Oyuncu Skoru: " + oyuncu.getSkor());
                kazananPanel.add(yeniSkorLabel, BorderLayout.NORTH); 
            } else if (bilgisayarDayaniklilik > oyuncuDayaniklilik) {
                int fark = bilgisayarDayaniklilik - oyuncuDayaniklilik;
                bilgisayar.setSkor(bilgisayar.getSkor() + fark);
                kazananLabel.setText("BİLGİSAYAR KAZANDI");
                kazananLabel.setForeground(Color.BLACK);
                yeniSkorLabel.setText("Yeni Bilgisayar Skoru: " + bilgisayar.getSkor());
                kazananPanel.add(yeniSkorLabel, BorderLayout.NORTH);
            } else {
                kazananLabel.setText("BERABERE!");
                kazananLabel.setForeground(Color.BLACK);
            }
        }
    
        kazananPanel.add(kazananLabel, BorderLayout.CENTER); 
        anaPanel.add(kazananPanel);

        skorPenceresi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
                    skorPenceresi.dispose();
                }
            }
        });
    
        skorPenceresi.setFocusable(true);
        skorPenceresi.requestFocusInWindow(); 

        skorPenceresi.setVisible(true);
    }

    public void kartSec()
    {
        secilenKartlar.clear();
        if (oyuncuAdi.equals("Bilgisayar")) {
            Random rastgele = new Random();
            List<String> ayniMiKontrol = new ArrayList<>(); 

            for (int i = 0; i < 3; i++) {
                List<Integer> uygunKartIndexleri = new ArrayList<>();
                for (int j = 0; j < kullanilmaListesi.size(); j++) {
                    if ((kullanilmaListesi.get(j) == 0 && !secilenKartlar.contains(kartListesi.get(j))) 
                        || (kartListesi.containsAll(secilenKartlar) && secilenKartlar.containsAll(kartListesi))) {
                        uygunKartIndexleri.add(j);
                    }
                }
        
                int secimIndex;
        
                if (!uygunKartIndexleri.isEmpty()) {
                    while (true) {
                        secimIndex = uygunKartIndexleri.get(rastgele.nextInt(uygunKartIndexleri.size()));
                        if (!ayniMiKontrol.contains(kartListesi.get(secimIndex))) {
                            break;
                        }
                    }
                } else {
                    while (true) {
                        secimIndex = rastgele.nextInt(kartListesi.size());
                        if (!ayniMiKontrol.contains(kartListesi.get(secimIndex))) {
                            break;
                        }
                    }
                }

                ayniMiKontrol.add(kartListesi.get(secimIndex));
                secilenKartlar.add(kartListesi.get(secimIndex)); 
                kullanilmaListesi.set(secimIndex, kullanilmaListesi.get(secimIndex) + 1); 
            }
 
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                writer.write("Bilgisayarin sectigi kartlar: " + ayniMiKontrol+"\n");
                
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

            ayniMiKontrol.clear(); 
    }
}

public int kartSec(List<JButton> butonlar, JFrame pencere, Oyuncu oyuncu) {
    oyuncu.secilenKartlar.clear();

    final boolean[] isSelectionComplete = {false};

    for (JButton buton : butonlar) {
        buton.addActionListener(e -> {
            if (oyuncu.secilenKartlar.size() < 3) {
                String secilenKart = ((JLabel) ((JPanel) buton.getParent()).getComponent(0)).getText(); 
                oyuncu.secilenKartlar.add(secilenKart); 

                int kartIndex = oyuncu.getKartListesi().indexOf(secilenKart);
                if (kartIndex != -1) {
                    oyuncu.getKullanilmaListesi().set(kartIndex, oyuncu.getKullanilmaListesi().get(kartIndex) + 1);
                }

                buton.setEnabled(false);
                buton.setBackground(Color.GREEN); 
                if (oyuncu.secilenKartlar.size() == 3) {
                    isSelectionComplete[0] = true;
                }
            }
        });
    }

    pencere.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (oyuncu.secilenKartlar.size() == 3) {
                    isSelectionComplete[0] = true; 
                } else {
                    JOptionPane.showMessageDialog(pencere, "Lütfen 3 kart seçiniz!", "Eksik Seçim", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    });

    pencere.requestFocus();

    while (!isSelectionComplete[0]) {
        try {
            Thread.sleep(100); 
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    return 1;
}

}