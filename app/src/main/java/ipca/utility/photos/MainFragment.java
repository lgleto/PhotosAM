package ipca.utility.photos;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private static final int CAMERA_PIC_REQUEST = 1001;

    List<Photo> photos=new ArrayList<>();

    ListView listView;
    PhotosAdapter photosAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        listView=view.findViewById(R.id.listViewPhotos);
        photosAdapter=new PhotosAdapter();
        listView.setAdapter(photosAdapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_PIC_REQUEST);
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode== RESULT_OK){
            switch (requestCode){
                case CAMERA_PIC_REQUEST:
                    Bundle bundle=data.getExtras();
                    Bitmap bm = (Bitmap) bundle.get("data");
                    File file=new File(Utils.saveBitmap(bm));
                    photos.add(new Photo(file.getName(),file.getPath()));
                    photosAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    class PhotosAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return photos.size();
        }

        @Override
        public Object getItem(int i) {
            return photos.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            if (view==null){
                view=getLayoutInflater().inflate(R.layout.row_photo ,null);
            }

            TextView textView = (TextView)view.findViewById(R.id.textViewTitle);
            final ImageView imageView= (ImageView)view.findViewById(R.id.imageView);

            textView.setText(photos.get(i).getName());
            String pathImage=photos.get(i).getPath();
            if (pathImage!=null){
                imageView.setImageBitmap(Utils.loadBitmap(pathImage));
            }


            view.setClickable(true);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Singleton.getInstance().setPhoto(photos.get(i));

                    PhotoDetailFragment photoDetailFragment= new PhotoDetailFragment();
                    FragmentManager fragmentManager= getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container,photoDetailFragment)
                            .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null)
                            .commit();

                }
            });

            return view;
        }
    }



}
