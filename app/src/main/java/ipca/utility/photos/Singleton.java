package ipca.utility.photos;

/**
 * Created by lourencogomes on 03/01/18.
 */

public class Singleton {

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    private Photo photo;



    private static Singleton instance = null;

    protected Singleton() {
        // Exists only to defeat instantiation.
    }

    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

}
