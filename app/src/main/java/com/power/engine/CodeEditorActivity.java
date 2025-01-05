package com.power.engine;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.schemes.HTMLScheme;
import io.github.rosemoe.sora.widget.schemes.SchemeDarcula;
import io.github.rosemoe.sora.widget.schemes.SchemeEclipse;
import io.github.rosemoe.sora.widget.schemes.SchemeGitHub;
import io.github.rosemoe.sora.widget.schemes.SchemeNotepadXX;
import io.github.rosemoe.sora.widget.schemes.SchemeVS2019;
import io.github.rosemoe.sora.langs.EmptyLanguage;
import io.github.rosemoe.sora.langs.desc.CDescription;
import io.github.rosemoe.sora.langs.desc.CppDescription;
import io.github.rosemoe.sora.langs.desc.JavaScriptDescription;
import io.github.rosemoe.sora.langs.html.HTMLLanguage;
import io.github.rosemoe.sora.langs.java.JavaLanguage;
import io.github.rosemoe.sora.langs.python.PythonLanguage;
import io.github.rosemoe.sora.langs.universal.UniversalLanguage;


import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.github.florent37.viewtooltip.*;
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
import java.util.regex.*;
import org.json.*;

public class CodeEditorActivity extends AppCompatActivity {
	
	private boolean isWordrap = false;
	private boolean isLandscape = false;
	public EditText replaceText;
	public EditText searchText;
	private boolean isLineNumber = false;
	private String path = "";
	
	private LinearLayout linear1;
	private CodeEditor editor;
	private LinearLayout linear7;
	private LinearLayout linear6;
	private LinearLayout linear5;
	private LinearLayout linear4;
	private LinearLayout linear3;
	private LinearLayout linear2;
	private ImageView imageview7;
	private ImageView imageview6;
	private ImageView imageview5;
	private ImageView imageview4;
	private ImageView imageview3;
	private ImageView imageview1;
	
	private AlertDialog.Builder dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.code_editor);
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
		editor = findViewById(R.id.editor);
		linear7 = findViewById(R.id.linear7);
		linear6 = findViewById(R.id.linear6);
		linear5 = findViewById(R.id.linear5);
		linear4 = findViewById(R.id.linear4);
		linear3 = findViewById(R.id.linear3);
		linear2 = findViewById(R.id.linear2);
		imageview7 = findViewById(R.id.imageview7);
		imageview6 = findViewById(R.id.imageview6);
		imageview5 = findViewById(R.id.imageview5);
		imageview4 = findViewById(R.id.imageview4);
		imageview3 = findViewById(R.id.imageview3);
		imageview1 = findViewById(R.id.imageview1);
		dialog = new AlertDialog.Builder(this);
		
		linear7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				editor.undo();
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				editor.redo();
			}
		});
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (isLineNumber) {
					editor.setLineNumberEnabled(true);
					imageview5.setImageResource(R.drawable.line_1);
				}
				else {
					editor.setLineNumberEnabled(false);
					imageview5.setImageResource(R.drawable.line);
				}
				isLineNumber = !isLineNumber;
			}
		});
		
		linear4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (isWordrap) {
					editor.setWordwrap(true);
				}
				else {
					editor.setWordwrap(false);
				}
				isWordrap = !isWordrap;
			}
		});
		
		linear3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final PopupWindow pw = new PopupWindow(CodeEditorActivity.this);
							View v = getLayoutInflater().inflate(R.layout.search,null);
				pw.setFocusable(true);
							pw.setContentView(v);
				
				searchText = v.findViewById(R.id.search_text);
				replaceText = v.findViewById(R.id.replace_text);
				ImageView search = v.findViewById(R.id.search_icon);
				ImageView replace = v.findViewById(R.id.replace_icon);
				
							pw.showAsDropDown(imageview3);
				search.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						
					}
				});
				replace.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						if (searchText.getText().toString().equals("") || replaceText.getText().toString().equals("")) {
							SketchwareUtil.showMessage(getApplicationContext(), "ERR_ IT_CANNOT_BE_REPLACED");
						}
						else {
							editor.setText(editor.getText().toString().replace(searchText.getText().toString(), replaceText.getText().toString()));
						}
					}
				});
				replaceText.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						dialog.setTitle("Replace Text");
						final EditText text = new EditText(CodeEditorActivity.this);
						LinearLayout.LayoutParams _Mk = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
						text.setLayoutParams(_Mk);
						dialog.setView(text);
						text.setHint("Replace Text");
						text.setText(replaceText.getText().toString());
						dialog.setMessage("please write the replacing text you want");
						dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								replaceText.setText(text.getText().toString());
							}
						});
						dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								
							}
						});
						dialog.create().show();
						replaceText.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)0, Color.TRANSPARENT, 0xFF313131));
						searchText.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)0, Color.TRANSPARENT, 0xFF313131));
					}
				});
				searchText.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						dialog.setTitle("Search Text");
						final EditText text = new EditText(CodeEditorActivity.this);
						LinearLayout.LayoutParams _Mk = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
						text.setLayoutParams(_Mk);
						dialog.setView(text);
						text.setHint("Search Text");
						text.setText(searchText.getText().toString());
						dialog.setMessage("please write the search text you want");
						dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								searchText.setText(text.getText().toString());
							}
						});
						dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								
							}
						});
						dialog.create().show();
					}
				});
				replaceText.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)0, Color.TRANSPARENT, 0xFF313131));
				searchText.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)0, Color.TRANSPARENT, 0xFF313131));
			}
		});
		
		linear2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (isLandscape) {
					setRequestedOrientation(android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				}
				else {
					setRequestedOrientation(android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
				isLandscape = !isLandscape;
			}
		});
	}
	
	private void initializeLogic() {
		android.graphics.drawable.GradientDrawable _BackgroundOf_editor = new android.graphics.drawable.GradientDrawable();
		_BackgroundOf_editor.setColors(new int[]{0xFF424242, 0xFF424242});
		_BackgroundOf_editor.setOrientation(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM);
		editor.setBackground(_BackgroundOf_editor);
		//editor.setTypefaceText(Typeface.createFromAsset(getAssets(), "fonts/youtubesansregular"+".ttf"));
		editor.setEditorLanguage(new EmptyLanguage());
		editor.setColorScheme(new SchemeDarcula());
		editor.setOverScrollEnabled(true);
		editor.setText(FileUtil.readFile(getIntent().getStringExtra("pathCode")));
		path = getIntent().getStringExtra("pathCode");
		isWordrap = false;
		isLandscape = false;
		isLineNumber = false;
		_sui_option_button(linear2, "");
		_sui_option_button(linear3, "");
		_sui_option_button(linear4, "");
		_sui_option_button(linear5, "");
		_sui_option_button(linear6, "");
		_sui_option_button(linear7, "");
	}
	
	@Override
	public void onBackPressed() {
		FileUtil.writeFile(getIntent().getStringExtra("pathCode"), editor.getText().toString());
		SketchwareUtil.showMessage(getApplicationContext(), "Saved Successfully");
		finish();
	}
	public void _sui_option_button(final View _view, final String _tooltip) {
		{
			// design
			android.graphics.drawable.GradientDrawable SUİ = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			int clrs [] = {0xFF424242,0xFF424242};
			SUİ= new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, clrs);
			SUİ.setCornerRadius(d*10);
			SUİ.setStroke(d*4,0xFF616161);
			_view.setElevation(d*15);
			android.graphics.drawable.RippleDrawable SUİ_RD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFF212121}), SUİ, null);
			_view.setBackground(SUİ_RD);
			_view.setClickable(true);
			// onlongclick
			_view.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View _view) {
							ViewTooltip
							        .on(CodeEditorActivity.this, _view)
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