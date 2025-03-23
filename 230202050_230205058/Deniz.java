import java.util.List;
import javax.swing.JFrame;
public abstract class Deniz extends SavasAraclari {

    protected abstract String getAltsinif();
    protected abstract void setAltsinif(String altsinif);
    protected abstract int getHavaVurusAvantaji();
    protected abstract void setHavaVurusAvantaji(int havaVurusAvantaji);

    public Deniz(int seviyePuani) {
        super(seviyePuani);
    }

    @Override
    public void KartPuaniGoster(JFrame pencere, int yer, int toplamKartSayisi, String oyuncuTipi, Oyuncu kullanici,
            String kart) {
        super.KartPuaniGoster(pencere, yer, toplamKartSayisi, oyuncuTipi, kullanici, kart);
    }

    @Override
    protected abstract void DurumGuncelle(String kart,String karsiKart, int hasar, Oyuncu hedefOyuncu, Oyuncu rakipOyuncu, int hedef);
}
