import java.util.List;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
public abstract class SavasAraclari {

    protected int seviyePuani;
    protected abstract int getDayaniklilik();
    protected abstract void setDayaniklilik(int dayaniklilik);
    protected abstract String getSinif();
    protected abstract void setSinif(String sinif);
    protected abstract int getVurus();
    protected abstract void setVurus(int vurus);

    public SavasAraclari(int seviyePuani) {
        this.seviyePuani = seviyePuani;
    }

    public void KartPuaniGoster(JFrame pencere, int yer, int toplamKartSayisi, String oyuncuTipi, Oyuncu kullanici, String kart) {
        int kartSayisi = Math.max(toplamKartSayisi, kullanici.getKartListesi().size()); 
        int kartWidth = 1200 / kartSayisi;
        kartWidth = Math.max(kartWidth, 100);
        kartWidth = Math.min(kartWidth, 200);
        int kartHeight = 250;
    
        String kartBase = kart.replaceAll("\\d+$", "");
    
        JPanel kartPanel = new JPanel();
        kartPanel.setLayout(new BorderLayout());
        kartPanel.setPreferredSize(new Dimension(kartWidth, kartHeight + 50)); 
    
        JLabel kartResimLabel;
        try {
            ImageIcon kartResmi = new ImageIcon("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\resimler\\" + kartBase + ".png");
            Image scaledImage = kartResmi.getImage().getScaledInstance(kartWidth, kartHeight, Image.SCALE_SMOOTH);
            kartResmi = new ImageIcon(scaledImage);
    
            kartResimLabel = new JLabel(kartResmi);
            kartResimLabel.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (Exception e) {
            kartResimLabel = new JLabel("Resim Yok", SwingConstants.CENTER);
            kartResimLabel.setOpaque(true);
            kartResimLabel.setBackground(Color.LIGHT_GRAY);
        }
        kartPanel.add(kartResimLabel, BorderLayout.CENTER);
    
        int kartIndex = kullanici.getKartListesi().indexOf(kart); 
        int seviyePuani = kullanici.getSeviyePuaniListesi().get(kartIndex); 
        int dayaniklilik = kullanici.getDayaniklilikListesi().get(kartIndex);
    
        JPanel bilgiPanel = new JPanel(); 
        bilgiPanel.setLayout(new GridLayout(2, 1)); 
        bilgiPanel.setOpaque(false); 
    
        JLabel dayaniklilikLabel = new JLabel("Dayanıklık: " + dayaniklilik, SwingConstants.CENTER);
        dayaniklilikLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
        bilgiPanel.add(dayaniklilikLabel); 
    
        JLabel seviyePuaniLabel = new JLabel("Seviye Puanı: " + seviyePuani, SwingConstants.CENTER);
        seviyePuaniLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bilgiPanel.add(seviyePuaniLabel); 
    
        kartPanel.add(bilgiPanel, BorderLayout.NORTH); 
    
        JLabel kartIsimLabel = new JLabel(kart, SwingConstants.CENTER);
        kartIsimLabel.setFont(new Font("Arial", Font.BOLD, 14)); 
        kartPanel.add(kartIsimLabel, BorderLayout.SOUTH); 
    
        JPanel hedefPanel;
        if (oyuncuTipi.equals("Bilgisayar")) {
            hedefPanel = (JPanel) pencere.getContentPane().getComponent(0); 
            hedefPanel.setLayout(new GridLayout(1, kartSayisi, 10, 10));
        } else {
            hedefPanel = (JPanel) pencere.getContentPane().getComponent(1); 
            hedefPanel.setLayout(new GridLayout(1, kartSayisi, 10, 10)); 
        }
    
        hedefPanel.add(kartPanel);
    
        pencere.revalidate();
        pencere.repaint();
    }
    
    protected abstract void DurumGuncelle(String kart,String karsiKart, int hasar, Oyuncu hedefOyuncu, Oyuncu rakipOyuncu, int hedef);
}
