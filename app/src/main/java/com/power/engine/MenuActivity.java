package com.power.engine;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.github.florent37.viewtooltip.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.rosemoe.sora.*;
import io.github.rosemoe.sora.langs.base.*;
import io.github.rosemoe.sora.langs.css3.*;
import io.github.rosemoe.sora.langs.html.*;
import io.github.rosemoe.sora.langs.java.*;
import io.github.rosemoe.sora.langs.python.*;
import io.github.rosemoe.sora.langs.textmate.*;
import io.github.rosemoe.sora.langs.universal.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import java.util.zip.*;

public class MenuActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	
	private Timer _timer = new Timer();
	
	private String subtitle = "";
	private String Folder = "";
	private double position = 0;
	private HashMap<String, Object> project = new HashMap<>();
	private String pickedPath = "";
	private String zipFilePath = "";
	private String destFolderPath = "";
	private String sourceFolderPath = "";
	
	private ArrayList<String> liststring = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> File_map = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private Button button2;
	private Button button1;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private LinearLayout linear14;
	private LinearLayout linear15;
	private TextView textview2;
	private TextView textview1;
	private TextView textview3;
	private TextView textview4;
	private TextView textview6;
	private SwipeRefreshLayout swiperefreshlayout1;
	private ListView listview1;
	
	private Intent it = new Intent();
	private Intent up_in = new Intent();
	private AlertDialog.Builder dialog1;
	private TimerTask t;
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent editor_intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.menu);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		button2 = findViewById(R.id.button2);
		button1 = findViewById(R.id.button1);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		linear11 = findViewById(R.id.linear11);
		linear12 = findViewById(R.id.linear12);
		linear14 = findViewById(R.id.linear14);
		linear15 = findViewById(R.id.linear15);
		textview2 = findViewById(R.id.textview2);
		textview1 = findViewById(R.id.textview1);
		textview3 = findViewById(R.id.textview3);
		textview4 = findViewById(R.id.textview4);
		textview6 = findViewById(R.id.textview6);
		swiperefreshlayout1 = findViewById(R.id.swiperefreshlayout1);
		listview1 = findViewById(R.id.listview1);
		dialog1 = new AlertDialog.Builder(this);
		fp.setType("*/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivity(new Intent(MenuActivity.this, CreateProjectActivity.class)); Animatoo.animateShrink(MenuActivity.this);
			}
		});
		
		linear8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivity(new Intent(MenuActivity.this, InfoActivity.class)); Animatoo.animateShrink(MenuActivity.this);
			}
		});
		
		linear10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivity(new Intent(MenuActivity.this, TutorialActivity.class)); Animatoo.animateShrink(MenuActivity.this);
			}
		});
		
		linear12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivity(new Intent(MenuActivity.this, ShopActivity.class)); Animatoo.animateShrink(MenuActivity.this);
			}
		});
		
		swiperefreshlayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				_RefreshData();
				t = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								swiperefreshlayout1.setRefreshing(false);
							}
						});
					}
				};
				_timer.schedule(t, (int)(700));
			}
		});
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				editor_intent.setClass(getApplicationContext(), EditorActivity.class);
				editor_intent.putExtra("path", Folder.concat(Uri.parse(liststring.get((int)(_position))).getLastPathSegment().concat("/")));
				editor_intent.putExtra("projePath", liststring.get((int)(_position)));
				startActivity(editor_intent);
			}
		});
	}
	
	private void initializeLogic() {
		getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		button2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		Folder = FileUtil.getPackageDataDir(getApplicationContext()).concat("/");
		_RefreshData();
		linear10.setVisibility(View.GONE);
		linear12.setVisibility(View.GONE);
		linear15.setVisibility(View.GONE);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				pickedPath = _filePath.get((int)(0));
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		_RefreshData();
	}
	
	public void _RefreshData() {
		listview1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); listview1.setItemsCanFocus(false);
		File_map.clear();
		FileUtil.listDir(Folder, liststring);
		Collections.sort(liststring, String.CASE_INSENSITIVE_ORDER);
		position = 0;
		for(int _repeat14 = 0; _repeat14 < (int)(liststring.size()); _repeat14++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("file", liststring.get((int)(position)));
				File_map.add(_item);
			}
			
			position++;
		}
		listview1.setAdapter(new Listview1Adapter(File_map));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
	}
	
	
	public void _ui_list(final View _view, final String _tooltip) {
		{
			// design
			android.graphics.drawable.GradientDrawable SUİ = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			int clrs [] = {0xFF424242,0xFF424242};
			SUİ= new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, clrs);
			SUİ.setCornerRadius(d*10);
			SUİ.setStroke(d*2,0xFF616161);
			_view.setElevation(d*15);
			android.graphics.drawable.RippleDrawable SUİ_RD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFF212121}), SUİ, null);
			_view.setBackground(SUİ_RD);
			_view.setClickable(true);
			// onlongclick
			_view.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View _view) {
							ViewTooltip
							        .on(MenuActivity.this, _view)
							        .position(ViewTooltip.Position.BOTTOM)
							        .text(_tooltip)
							         .shadowColor(0xFF424242)
							.color(0xFF616161)
							.corner(15)
							        .show();
							return true;
					}
			});
		}
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.cus1, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			final LinearLayout linear11 = _view.findViewById(R.id.linear11);
			final LinearLayout linear10 = _view.findViewById(R.id.linear10);
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final ImageView imageview8 = _view.findViewById(R.id.imageview8);
			final ImageView imageview7 = _view.findViewById(R.id.imageview7);
			final ImageView imageview6 = _view.findViewById(R.id.imageview6);
			
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
			textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
			textview1.setText(Uri.parse(liststring.get((int)(_position))).getLastPathSegment());
			project = new Gson().fromJson(FileUtil.readFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(Uri.parse(liststring.get((int)(_position))).getLastPathSegment().concat("/system/".concat("settings.pre"))))), new TypeToken<HashMap<String, Object>>(){}.getType());
			textview2.setText(project.get("package").toString());
			imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(Uri.parse(liststring.get((int)(_position))).getLastPathSegment().concat("/system/".concat("icon.png")))), 1024, 1024));
			imageview8.setColorFilter(0xFFB0BCE5, PorterDuff.Mode.MULTIPLY);
			{
				// design
				android.graphics.drawable.GradientDrawable SUİ = new android.graphics.drawable.GradientDrawable();
				int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
				int clrs [] = {0xFF313131,0xFF313131};
				SUİ= new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, clrs);
				SUİ.setCornerRadius(d*10);
				SUİ.setStroke(d*2,0xFF616161);
				linear1.setElevation(d*15);
				android.graphics.drawable.RippleDrawable SUİ_RD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFF212121}), SUİ, null);
				linear1.setBackground(SUİ_RD);
				linear1.setClickable(true);
			}
			_ui_list(linear9, "settings");
			_ui_list(linear10, "backup");
			_ui_list(linear11, "tooltip");
			linear9.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					up_in.putExtra("name", Uri.parse(liststring.get((int)(_position))).getLastPathSegment());
					up_in.setClass(getApplicationContext(), UpdateProjectActivity.class);
					startActivity(up_in);
				}
			});
			linear10.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					sourceFolderPath = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(Uri.parse(liststring.get((int)(_position))).getLastPathSegment().concat("/")));
					zipFilePath = FileUtil.getExternalStorageDir().concat("/Power Engine/Backups/");
					SketchwareUtil.showMessage(getApplicationContext(), "Backupped to : ".concat(zipFilePath));
				}
			});
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					editor_intent.setClass(getApplicationContext(), EditorActivity.class);
					editor_intent.putExtra("path", Folder.concat(Uri.parse(liststring.get((int)(_position))).getLastPathSegment().concat("/")));
					startActivity(editor_intent);
				}
			});
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}