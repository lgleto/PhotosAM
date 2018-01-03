package ipca.utility.photos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoDetailFragment extends Fragment {


    public PhotoDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo_detail, container, false);

        ImageView imageView= view.findViewById(R.id.imageViewPhoto);

        String pathImage =Singleton.getInstance().getPhoto().getPath();
        imageView.setImageBitmap(Utils.loadBitmap(pathImage));

        return view;
    }

}
