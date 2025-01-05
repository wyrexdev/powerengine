package com.power.engine;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.github.florent37.viewtooltip.*;
import com.google.android.material.textfield.*;
import com.google.gson.Gson;
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
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class CreateProjectActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	
	private String type = "";
	private HashMap<String, Object> project = new HashMap<>();
	private String json = "";
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear7;
	private LinearLayout linear11;
	private LinearLayout linear6;
	private LinearLayout linear8;
	private LinearLayout linear10;
	private TextView textview1;
	private ImageView imageview1;
	private TextInputLayout textinputlayout2;
	private TextInputLayout textinputlayout1;
	private LinearLayout linear12;
	private EditText edittext2;
	private EditText edittext1;
	private TextInputLayout textinputlayout3;
	private LinearLayout linear13;
	private TextInputLayout textinputlayout4;
	private EditText edittext3;
	private EditText edittext4;
	private RadioGroup radiogroup1;
	private RadioButton radiobutton1;
	private LinearLayout linear15;
	private RadioButton radiobutton2;
	private Button button1;
	
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.create_project);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		linear4 = findViewById(R.id.linear4);
		linear7 = findViewById(R.id.linear7);
		linear11 = findViewById(R.id.linear11);
		linear6 = findViewById(R.id.linear6);
		linear8 = findViewById(R.id.linear8);
		linear10 = findViewById(R.id.linear10);
		textview1 = findViewById(R.id.textview1);
		imageview1 = findViewById(R.id.imageview1);
		textinputlayout2 = findViewById(R.id.textinputlayout2);
		textinputlayout1 = findViewById(R.id.textinputlayout1);
		linear12 = findViewById(R.id.linear12);
		edittext2 = findViewById(R.id.edittext2);
		edittext1 = findViewById(R.id.edittext1);
		textinputlayout3 = findViewById(R.id.textinputlayout3);
		linear13 = findViewById(R.id.linear13);
		textinputlayout4 = findViewById(R.id.textinputlayout4);
		edittext3 = findViewById(R.id.edittext3);
		edittext4 = findViewById(R.id.edittext4);
		radiogroup1 = findViewById(R.id.radiogroup1);
		radiobutton1 = findViewById(R.id.radiobutton1);
		linear15 = findViewById(R.id.linear15);
		radiobutton2 = findViewById(R.id.radiobutton2);
		button1 = findViewById(R.id.button1);
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
		
		edittext2.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				textinputlayout2.setErrorEnabled(false);
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				textinputlayout1.setErrorEnabled(false);
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		radiobutton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					type = "Landscape";
				}
			}
		});
		
		radiobutton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					type = "Portrait";
				}
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1.getText().toString().equals("")) {
					textinputlayout1.setError("Boş Bırakılamaz!");
					textinputlayout1.setErrorEnabled(true);
				}
				else {
					if (edittext2.getText().toString().equals("")) {
						textinputlayout2.setError("Boş Bırakılamaz!");
						textinputlayout2.setErrorEnabled(true);
					}
					else {
						if (FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(edittext2.getText().toString().concat("/"))))) {
							SketchwareUtil.showMessage(getApplicationContext(), "Bu İsimde Bir Proje Bulunmakta!");
						}
						else {
							try{
									BitmapDrawable imageview1BD = (BitmapDrawable) imageview1.getDrawable();
									Bitmap imageview1B = imageview1BD.getBitmap();
									FileOutputStream imageview1FOS = null;
									File imageview1F = Environment.getExternalStorageDirectory();
									File imageview1F2 = new File(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(edittext2.getText().toString().concat("/system/"))));
									imageview1F2.mkdirs();
									String imageview1FN = "icon.png";
									File imageview1F3 = new File(imageview1F2, imageview1FN);
									imageview1FOS = new FileOutputStream(imageview1F3); 
									imageview1B.compress(Bitmap.CompressFormat.JPEG, 100, imageview1FOS);
									imageview1FOS.flush();
									imageview1FOS.close(); 
									Intent imageview1I = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
									imageview1I.setData(Uri.fromFile(imageview1F)); sendBroadcast(imageview1I);
							}catch(Exception e){
							}
							project = new HashMap<>();
							project.put("package", edittext1.getText().toString());
							project.put("name", edittext2.getText().toString());
							project.put("versionUp", edittext4.getText().toString());
							project.put("version", edittext3.getText().toString());
							project.put("defaultScene", "/content/scenes/Game.pew");
							project.put("screenType", type);
							FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(edittext2.getText().toString().concat("/system/".concat("settings.pre")))), new Gson().toJson(project));
							FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(edittext2.getText().toString().concat("/"))), "");
							FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(edittext2.getText().toString().concat("/content/"))), "");
							_createJsonProject("");
							FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(edittext2.getText().toString().concat("/content/scenes/Game.pew"))), json);
							FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(edittext2.getText().toString().concat("/content/geometry/"))), "");
							FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(edittext2.getText().toString().concat("/content/materials/"))), "");
							FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat(edittext2.getText().toString().concat("/content/Services/"))), "");
							finish();
						}
					}
				}
			}
		});
	}
	
	private void initializeLogic() {
		getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		edittext3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		edittext4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		radiobutton1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		radiobutton2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"));
		radiobutton1.setChecked(true);
		radiobutton2.setChecked(false);
		type = "Landscape";
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
				imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	public void _createJsonProject(final String _projectFile) {
		JSONObject jo = null;
		try {
			    jo = new JSONObject();
			    jo.put("bR","1");
			    jo.put("bG","1");
			    jo.put("bB","1");
			    jo.put("bA","1");
			    
			     JSONObject sq = new JSONObject();
			     sq.put("type","Square");
			
			     JSONArray comps = new JSONArray();
			     comps.put(sq);
			
			    JSONObject empty = new JSONObject();
			    empty.put("name","Empty");
			    empty.put("visible","true");
			    empty.put("pX",0);
			    empty.put("pY",0);
			    empty.put("sX",6);
			    empty.put("sY",3);
			    empty.put("rX",0);
			    empty.put("rY",0);
			    empty.put("rZ",0);
			    empty.put("texture","null");
			    empty.put("cR",1);
			    empty.put("cG",0);
			    empty.put("cB",0);
			    empty.put("cA",1);
			
			    empty.put("Components",comps);
			
			    JSONArray objects = new JSONArray();
			    objects.put(empty);
			
			    jo.put("Objects",objects);
		} catch (Exception e) {
		}
		
		json = jo.toString();
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