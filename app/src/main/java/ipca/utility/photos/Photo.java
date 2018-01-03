package ipca.utility.photos;

/**
 * Created by lourencogomes on 03/01/18.
 */

public class Photo {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    String path;

    public Photo(String name, String path) {
        this.name = name;
        this.path = path;
    }
}
