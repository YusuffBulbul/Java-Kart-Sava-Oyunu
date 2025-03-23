import java.util.List;
import javax.swing.JFrame;

public abstract class Hava extends SavasAraclari {

    protected abstract String getAltsinif();
    protected abstract void setAltsinif(String altsinif);
    protected abstract int getKaraVurusAvantaji();
    protected abstract void setKaraVurusAvantaji(int karaVurusAvantaji);

    public Hava(int seviyePuani) {
        super(seviyePuani);
    }

    @Override
    public void KartPuaniGoster(JFrame pencere, int yer, int toplamKartSayisi, String oyuncuTipi, Oyuncu kullanici,
            String kart) {
        super.KartPuaniGoster(pencere, yer, toplamKartSayisi, oyuncuTipi, kullanici, kart);
    }
    @Override
    protected int getDayaniklilik() {
        return 0;
    }
    @Override
    protected String getSinif() {
        return null;
    }
    @Override
    protected int getVurus() {
        return 0;
    }
    @Override
    protected void setDayaniklilik(int dayaniklilik) {
        
    }
    @Override
    protected void setSinif(String sinif) {
        
    }
    @Override
    protected void setVurus(int vurus) {
        
    }
    @Override
    protected abstract void DurumGuncelle(String kart,String karsiKart, int hasar, Oyuncu hedefOyuncu, Oyuncu rakipOyuncu, int hedef);
}
