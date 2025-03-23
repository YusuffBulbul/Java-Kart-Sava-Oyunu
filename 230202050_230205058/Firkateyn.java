import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Firkateyn extends Deniz {

    private String altsinif;
    private int havaVurusAvantaji;
    private int dayaniklilik;
    private String sinif;
    private int vurus;

    public Firkateyn(int seviyePuani, String altsinif, int havaVurusAvantaji, int dayaniklilik, String sinif, int vurus) {
        super(seviyePuani);
        this.altsinif = altsinif;
        this.havaVurusAvantaji = havaVurusAvantaji;
        this.dayaniklilik = dayaniklilik;
        this.sinif = sinif;
        this.vurus = vurus;
    }

    @Override
    protected String getAltsinif() {
        return altsinif;
    }

    @Override
    protected void setAltsinif(String altsinif) {
        this.altsinif = altsinif;
    }

    @Override
    protected int getHavaVurusAvantaji() {
        return havaVurusAvantaji;
    }

    @Override
    protected void setHavaVurusAvantaji(int havaVurusAvantaji) {
        this.havaVurusAvantaji = havaVurusAvantaji;
    }

    @Override
    public int getDayaniklilik() {
        return dayaniklilik;
    }

    @Override
    public void setDayaniklilik(int dayaniklilik) {
        this.dayaniklilik = dayaniklilik;
    }

    @Override
    public String getSinif() {
        return sinif;
    }

    @Override
    public void setSinif(String sinif) {
        this.sinif = sinif;
    }

    @Override
    public int getVurus() {
        return vurus;
    }

    @Override
    public void setVurus(int vurus) {
        this.vurus = vurus;
    }

    @Override
    public void KartPuaniGoster(JFrame pencere, int yer, int toplamKartSayisi, String oyuncuTipi, Oyuncu kullanici,
            String kart) {
        super.KartPuaniGoster(pencere, yer, toplamKartSayisi, oyuncuTipi, kullanici, kart);
    }

    @Override
    protected void DurumGuncelle(String kart, String karsiKart, int hasar, Oyuncu oyuncu, Oyuncu bilgisayar, int hedef) {
        List<Integer> geciciDayaniklilikListesi = new ArrayList<>(oyuncu.getDayaniklilikListesi());
    
        int kartIndex = oyuncu.getKartListesi().indexOf(kart);
    
        if (kartIndex < 0) {
            System.out.println("HATA: Oyuncu karti bulunamadi! Kart: " + kart);
            return;
        }
    
        int mevcutDayaniklilik = geciciDayaniklilikListesi.get(kartIndex);
        int yeniDayaniklilik = mevcutDayaniklilik - hasar; 
        if(yeniDayaniklilik<0)
        {
            yeniDayaniklilik=0;
        }
        geciciDayaniklilikListesi.set(kartIndex, yeniDayaniklilik);
        oyuncu.setDayaniklilikListesi(geciciDayaniklilikListesi);
        
        BufferedWriter writer = null; 
            try {
                writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                writer.write("Oyuncu karti: " + kart + " dayanikliligi guncellendi: " + yeniDayaniklilik+"\n");
                
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

        if (yeniDayaniklilik == 0) {
            int kartSeviyesi = oyuncu.getSeviyePuaniListesi().get(kartIndex);
            int karsiKartIndex = bilgisayar.getKartListesi().indexOf(karsiKart);
    
            if (karsiKartIndex >= 0) {
                int mevcutKarsiKartSeviyePuani = bilgisayar.getSeviyePuaniListesi().get(karsiKartIndex);
                int eklenenSeviye = (kartSeviyesi < 10) ? 10 : kartSeviyesi; 
                int yeniKarsiKartSeviyePuani = mevcutKarsiKartSeviyePuani + eklenenSeviye;

                bilgisayar.getSeviyePuaniListesi().set(karsiKartIndex, yeniKarsiKartSeviyePuani);
    
                try {
                    writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                    writer.write("Bilgisayar karti: " + karsiKart + " seviyesine " + eklenenSeviye + " eklendi."+"\n");
                    
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
            } else {
                System.out.println("HATA: Karsi kart bilgisayarin kart listesinde bulunamadi! Kart: " + karsiKart);
            }
            
        }
        
    }
    
    


    protected void DurumGuncelle(String kart, String karsiKart, int hasar, Oyuncu oyuncu, Oyuncu bilgisayar) {
        List<Integer> geciciDayaniklilikListesi2 = new ArrayList<>(bilgisayar.getDayaniklilikListesi());
        int kartIndex = bilgisayar.getKartListesi().indexOf(kart);
    
        if (kartIndex < 0) {
            System.out.println("HATA: Bilgisayar karti bulunamadi! Kart: " + kart);
            return;
        }
    
        int mevcutDayaniklilik = geciciDayaniklilikListesi2.get(kartIndex);
        int yeniDayaniklilik = mevcutDayaniklilik - hasar; 
        if(yeniDayaniklilik<0)
        {
            yeniDayaniklilik=0;
        }
        geciciDayaniklilikListesi2.set(kartIndex, yeniDayaniklilik);
        bilgisayar.setDayaniklilikListesi(geciciDayaniklilikListesi2);

        BufferedWriter writer = null; 
            try {
                writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                writer.write("Bilgisayar karti: " + kart + " dayanikliligi güncellendi: " + yeniDayaniklilik+"\n");
                
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
    
        if (yeniDayaniklilik == 0) {
            int kartSeviyesi = bilgisayar.getSeviyePuaniListesi().get(kartIndex);
            int karsiKartIndex = oyuncu.getKartListesi().indexOf(karsiKart);
    
            if (karsiKartIndex >= 0) {
                int mevcutKarsiKartSeviyePuani = oyuncu.getSeviyePuaniListesi().get(karsiKartIndex);
                int eklenenSeviye = (kartSeviyesi < 10) ? 10 : kartSeviyesi; 
                int yeniKarsiKartSeviyePuani = mevcutKarsiKartSeviyePuani + eklenenSeviye;
                oyuncu.getSeviyePuaniListesi().set(karsiKartIndex, yeniKarsiKartSeviyePuani);
    
                try {
                    writer = new BufferedWriter(new FileWriter("C:\\Users\\yusuf\\OneDrive\\Masaüstü\\230202050_230205058\\Prolab2.txt", true));
                    writer.write("Oyuncu karti: " + karsiKart + " seviyesine " + eklenenSeviye + " eklendi."+"\n");
                    
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
            } else {
                System.out.println("HATA: Karsi kart oyuncunun kart listesinde bulunamadi! Kart: " + karsiKart);
            }
            

        }
        
    }
}
