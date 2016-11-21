package androidinterview.com.androidgalleryview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

	private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/misfotos/";

	private File file = new File(ruta_fotos);
	private Button boton;
	Gallery galleryView;
	ImageView imgView;

	//images from drawable
	private File [] imageResource = file.listFiles();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imgView = (ImageView) findViewById(R.id.imageView);
		galleryView = (Gallery) findViewById(R.id.gallery);

		try {
			Picasso.with(MainActivity.this).load(imageResource[0]).into(imgView);
			galleryView.setAdapter(new myImageAdapter(this));
		}
		catch(Exception e)
        {

        }
		//gallery image onclick event
		galleryView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int i, long id) {

				Picasso.with(MainActivity.this).load(imageResource[i]).into(imgView);
				int imagePosition = i + 1;

				//Toast.makeText(getApplicationContext(), "selecciono la imagen = " + imagePosition, Toast.LENGTH_LONG).show();
			}
		});

		boton = (Button) findViewById(R.id.btnTomaFoto);
		//Si no existe crea la carpeta donde se guardaran las fotos
		file.mkdirs();
		//accion para el boton
		boton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String file = ruta_fotos + getCode() + ".jpg";

				File mi_foto = new File(file);
				try {
					mi_foto.createNewFile();
				} catch (IOException ex) {
					Log.e("ERROR ", "Error:" + ex);
				}
				//
				Uri uri = Uri.fromFile(mi_foto);
				//Abre la camara para tomar la foto
				Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

				//Guarda imagen
				cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

				//Retorna a la actividad

				startActivityForResult(cameraIntent, 0);

				act();
			}

		});

	}

	@SuppressLint("SimpleDateFormat")
	private String getCode() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
		String date = dateFormat.format(new Date());
		String photoCode = "pic_" + date;

		return photoCode;
	}

	public void act()
	{
		imageResource = file.listFiles();
		galleryView.setAdapter(new myImageAdapter(MainActivity.this));
	}


	public class myImageAdapter extends BaseAdapter {

		private Context mcontext;


		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView mgalleryView = new ImageView(mcontext);

			Picasso.with(MainActivity.this).load(imageResource[position]).into(mgalleryView);


			mgalleryView.setLayoutParams(new Gallery.LayoutParams(150, 150));
			mgalleryView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

			mgalleryView.setPadding(3, 3, 3, 3);

			return mgalleryView;
		}

		public myImageAdapter(Context context) {
			mcontext = context;
		}

		public int getCount() {
			return imageResource.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}
	}

}