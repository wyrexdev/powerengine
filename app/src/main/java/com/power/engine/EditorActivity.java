package com.power.engine;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.github.florent37.viewtooltip.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
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
import java.text.DecimalFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.ListAdapter;

public class EditorActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private double position = 0;
	private boolean isAppearance = false;
	private boolean isColor = false;
	private boolean isTransform = false;
	private boolean isText = false;
	private boolean isScript = false;
	private boolean isBehaviour = false;
	private String pathForDrawer = "";
	private String file = "";
	private boolean isDarkMode = false;
	private double bottomY = 0;
	private double leftX = 0;
	private double rigthX = 0;
	private HashMap<String, Object> proje = new HashMap<>();
	private String defaultScenePath = "";
	private String settings = "";
	private String json = "";
	public JSONObject jo;
	private double posObj = 0;
	private boolean isObject = false;
	private String names = "";
	private String pX = "";
	private String pY = "";
	private double posWE = 0;
	private String pos = "";
	private double zpxt = 0;
	private String sX = "";
	private String sY = "";
	private String cR = "";
	private String cG = "";
	private String cB = "";
	private String cA = "";
	private boolean isSquare = false;
	private double listHeight = 0;
	private String dragPath = "";
	private double compCount = 0;
	private double UNBOUNDED = 0;
	private double squareCount = 0;
	private String colorRGB = "";
	private double posTexture = 0;
	private double posScript = 0;
	private double compPos = 0;
	private String rX = "";
	private String rY = "";
	private String rZ = "";
	private String texture = "";
	private boolean update = false;
	public EditText compText;
	private String text = "";
	private String textValue = "";
	private String textS = "";
	
	private ArrayList<HashMap<String, Object>> File_map = new ArrayList<>();
	private ArrayList<String> liststring = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> compList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> textures = new ArrayList<>();
	private ArrayList<String> textureString = new ArrayList<>();
	private ArrayList<String> scriptString = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> scripts = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout rightMenu;
	private LinearLayout linear4;
	private LinearLayout bottom;
	private LinearLayout leftMenu;
	private LinearLayout linear49;
	private HorizontalScrollView hscroll1;
	private ScrollView vscroll2;
	private LinearLayout linear8;
	private TextView textview1;
	private LinearLayout linear16;
	private TextView textview36;
	private RecyclerView recyclerview2;
	private LinearLayout leftSc;
	private LinearLayout linear57;
	private LinearLayout linear66;
	private LinearLayout linear59;
	private LinearLayout linear6;
	private LinearLayout linear73;
	private LinearLayout linear74;
	private ImageView imageview6;
	private ImageView imageview23;
	private ImageView imageview24;
	private LinearLayout bottomSc;
	private LinearLayout linear61;
	private LinearLayout linear15;
	private HorizontalScrollView hscroll3;
	private LinearLayout linear12;
	private TextView textview3;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private RecyclerView recyclerview1;
	private GridView gridview1;
	private LinearLayout rigthSc;
	private LinearLayout comps;
	private LinearLayout insp;
	private LinearLayout linear9;
	private LinearLayout linear63;
	private TextView textview38;
	private LinearLayout transformComponent;
	private TextView textview2;
	private EditText name;
	private ListView listview;
	private LinearLayout linear65;
	private TextView textview39;
	
	private TimerTask tmr;
	private AlertDialog.Builder dialog;
	private Intent it = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.editor);
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
		linear3 = findViewById(R.id.linear3);
		rightMenu = findViewById(R.id.rightMenu);
		linear4 = findViewById(R.id.linear4);
		bottom = findViewById(R.id.bottom);
		leftMenu = findViewById(R.id.leftMenu);
		linear49 = findViewById(R.id.linear49);
		hscroll1 = findViewById(R.id.hscroll1);
		vscroll2 = findViewById(R.id.vscroll2);
		linear8 = findViewById(R.id.linear8);
		textview1 = findViewById(R.id.textview1);
		linear16 = findViewById(R.id.linear16);
		textview36 = findViewById(R.id.textview36);
		recyclerview2 = findViewById(R.id.recyclerview2);
		leftSc = findViewById(R.id.leftSc);
		linear57 = findViewById(R.id.linear57);
		linear66 = findViewById(R.id.linear66);
		linear59 = findViewById(R.id.linear59);
		linear6 = findViewById(R.id.linear6);
		linear73 = findViewById(R.id.linear73);
		linear74 = findViewById(R.id.linear74);
		imageview6 = findViewById(R.id.imageview6);
		imageview23 = findViewById(R.id.imageview23);
		imageview24 = findViewById(R.id.imageview24);
		bottomSc = findViewById(R.id.bottomSc);
		linear61 = findViewById(R.id.linear61);
		linear15 = findViewById(R.id.linear15);
		hscroll3 = findViewById(R.id.hscroll3);
		linear12 = findViewById(R.id.linear12);
		textview3 = findViewById(R.id.textview3);
		linear17 = findViewById(R.id.linear17);
		linear18 = findViewById(R.id.linear18);
		recyclerview1 = findViewById(R.id.recyclerview1);
		gridview1 = findViewById(R.id.gridview1);
		rigthSc = findViewById(R.id.rigthSc);
		comps = findViewById(R.id.comps);
		insp = findViewById(R.id.insp);
		linear9 = findViewById(R.id.linear9);
		linear63 = findViewById(R.id.linear63);
		textview38 = findViewById(R.id.textview38);
		transformComponent = findViewById(R.id.transformComponent);
		textview2 = findViewById(R.id.textview2);
		name = findViewById(R.id.name);
		listview = findViewById(R.id.listview);
		linear65 = findViewById(R.id.linear65);
		textview39 = findViewById(R.id.textview39);
		dialog = new AlertDialog.Builder(this);
		
		linear16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				isObject = false;
			}
		});
		
		textview36.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final PopupWindow pw = new PopupWindow(EditorActivity.this);
							View v = getLayoutInflater().inflate(R.layout.object_create,null);
				pw.setFocusable(true);
							pw.setContentView(v);
				
				LinearLayout empty = v.findViewById(R.id.empty);
				LinearLayout square = v.findViewById(R.id.square);
				LinearLayout text = v.findViewById(R.id.text);
				
				pw.showAsDropDown(textview36);
				empty.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						list1.clear();
						try {
							JSONObject empty = new JSONObject();
							    empty.put("name","Empty " +jo.getJSONArray("Objects").length());
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
							    JSONArray compsj = new JSONArray();
							    empty.put("Components",compsj);
							jo.getJSONArray("Objects").put(empty);
						} catch (Exception e) {}
						try {
							for(int i = 0; i < jo.getJSONArray("Objects").length(); i++){
								{
									HashMap<String, Object> _item = new HashMap<>();
									_item.put("file", "obj");
									list1.add(_item);
								}
								
							}
						} catch (Exception e) {
						}
						recyclerview2.setAdapter(new Recyclerview2Adapter(list1));
						pw.dismiss();
					}
				});
				square.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						list1.clear();
						try {
							JSONObject empty = new JSONObject();
							    empty.put("name","Square " +jo.getJSONArray("Objects").length());
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
							    JSONArray compsj = new JSONArray();
							    
							    JSONObject sq = new JSONObject();
							    sq.put("type","Square");
							    
							    compsj.put(sq);
							    
							    empty.put("Components",compsj);
							jo.getJSONArray("Objects").put(empty);
						} catch (Exception e) {}
						try {
							for(int i = 0; i < jo.getJSONArray("Objects").length(); i++){
								{
									HashMap<String, Object> _item = new HashMap<>();
									_item.put("file", "obj");
									list1.add(_item);
								}
								
							}
						} catch (Exception e) {
						}
						recyclerview2.setAdapter(new Recyclerview2Adapter(list1));
						pw.dismiss();
					}
				});
				text.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						list1.clear();
						try {
							JSONObject empty = new JSONObject();
							    empty.put("name","Text " +jo.getJSONArray("Objects").length());
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
							    JSONArray compsj = new JSONArray();
							    
							    JSONObject sq = new JSONObject();
							    sq.put("type","Text");
							    sq.put("text","TextObject");
							    
							    compsj.put(sq);
							    
							    empty.put("Components",compsj);
							jo.getJSONArray("Objects").put(empty);
						} catch (Exception e) {}
						try {
							for(int i = 0; i < jo.getJSONArray("Objects").length(); i++){
								{
									HashMap<String, Object> _item = new HashMap<>();
									_item.put("file", "obj");
									list1.add(_item);
								}
								
							}
						} catch (Exception e) {
						}
						recyclerview2.setAdapter(new Recyclerview2Adapter(list1));
						pw.dismiss();
					}
				});
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				FileUtil.writeFile(getIntent().getStringExtra("path").concat(defaultScenePath), jo.toString());
				SketchwareUtil.showMessage(getApplicationContext(), "Successfully Saved");
			}
		});
		
		linear73.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				it.setClass(getApplicationContext(), RunActivity.class);
				it.putExtra("screenPath", getIntent().getStringExtra("path").concat(defaultScenePath));
				it.putExtra("path", getIntent().getStringExtra("path"));
				startActivity(it);
			}
		});
		
		linear74.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				it.putExtra("path", getIntent().getStringExtra("path"));
				it.setClass(getApplicationContext(), UploadFileActivity.class);
				startActivity(it);
			}
		});
		
		bottomSc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		gridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (Uri.parse(liststring.get((int)(_position))).getLastPathSegment().endsWith(".lua") || Uri.parse(liststring.get((int)(_position))).getLastPathSegment().endsWith(".js")) {
					it.setClass(getApplicationContext(), CodeEditorActivity.class);
					it.putExtra("pathCode", liststring.get((int)(_position)));
					startActivity(it);
				}
				else {
					if (Uri.parse(liststring.get((int)(_position))).getLastPathSegment().endsWith(".gui")) {
						it.setClass(getApplicationContext(), UiEditorActivity.class);
						it.putExtra("pathCode", liststring.get((int)(_position)));
						startActivity(it);
					}
					else {
						
					}
				}
			}
		});
		
		gridview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (FileUtil.isFile(liststring.get((int)(_position)))) {
					
				}
				return true;
			}
		});
		
		textview38.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final PopupWindow pw = new PopupWindow(EditorActivity.this);
							View v = getLayoutInflater().inflate(R.layout.componen_add,null);
				pw.setFocusable(true);
							pw.setContentView(v);
				
				LinearLayout sq = v.findViewById(R.id.square);
				LinearLayout sc = v.findViewById(R.id.script);
				LinearLayout txt = v.findViewById(R.id.text);
				
				pw.showAsDropDown(textview38);
				sq.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						compList.clear();
						try { 
							JSONObject sq = new JSONObject();
							sq.put("type", "Square");
							jo. getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").put(sq);
						} catch (Exception e) {}
						try {
							for(int i = 0; i < jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").length(); i++){
								{
									HashMap<String, Object> _item = new HashMap<>();
									_item.put("file", "comp");
									compList.add(_item);
								}
								
							}
						} catch (Exception e) {}
						listview.setAdapter(new ListviewAdapter(compList));
						((BaseAdapter)listview.getAdapter()).notifyDataSetChanged();
						pw.dismiss();
					}
				});
				sc.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						compList.clear();
						try { 
							JSONObject sc = new JSONObject();
							sc.put("type", "Script");
							sc.put("path", "null");
							jo. getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").put(sc);
						} catch (Exception e) {}
						try {
							for(int i = 0; i < jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").length(); i++){
								{
									HashMap<String, Object> _item = new HashMap<>();
									_item.put("file", "comp");
									compList.add(_item);
								}
								
							}
						} catch (Exception e) {}
						listview.setAdapter(new ListviewAdapter(compList));
						((BaseAdapter)listview.getAdapter()).notifyDataSetChanged();
						pw.dismiss();
					}
				});
				txt.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						compList.clear();
						try { 
							JSONObject sc = new JSONObject();
							sc.put("type", "Text");
							sc.put("text", "TextComponent");
							jo. getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").put(sc);
						} catch (Exception e) {}
						try {
							for(int i = 0; i < jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").length(); i++){
								{
									HashMap<String, Object> _item = new HashMap<>();
									_item.put("file", "comp");
									compList.add(_item);
								}
								
							}
						} catch (Exception e) {}
						listview.setAdapter(new ListviewAdapter(compList));
						((BaseAdapter)listview.getAdapter()).notifyDataSetChanged();
						pw.dismiss();
					}
				});
			}
		});
		
		name.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				try { 
					if(name.getText().toString().equals("")){
					} else {
						jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("name",name.getText().toString());
					} 
				} catch (Exception e) { }
				_setObjectList();
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				compPos = _position;
			}
		});
	}
	
	private void initializeLogic() {
		listHeight = 100;
		isObject = false;
		isAppearance = false;
		isColor = false;
		isTransform = false;
		isText = false;
		isScript = false;
		isBehaviour = false;
		isSquare = false;
		compText = new EditText(this);
		
		getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		TreeViewList.textColor = Color.WHITE;
		TreeViewList.backgroundColor = 0xFF424242;
		TreeViewList.darkMode = this.isDarkMode;
		pathForDrawer = getIntent().getStringExtra("path").concat("");
		loadFilesTotree(pathForDrawer, recyclerview1, Uri.parse(pathForDrawer).getLastPathSegment());
		textview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
		textview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
		textview36.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
		name.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
		_sui_option_button(linear6, "Save");
		_sui_option_button(linear73, "Run");
		_sui_option_button(linear74, "Upload File");
		
		bottomSc.setOnTouchListener(new View.OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event){
				LayoutParams lp = bottom.getLayoutParams();
				if(event.getY() > bottomY){
					    lp.height = bottom.getHeight() - 14;
					    bottom.setLayoutParams(lp);
					    LayoutParams le = linear59.getLayoutParams();
					    le.height = LayoutParams.MATCH_PARENT;
				} else {
					    lp.height = bottom.getHeight() + 14;
					    bottom.setLayoutParams(lp);
					    LayoutParams le = linear59.getLayoutParams();
					    le.height = LayoutParams.MATCH_PARENT;
				}
				
				bottomY = event.getY();
				
				return true;
			}
		});
		
		leftSc.setOnTouchListener(new View.OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event){
				LayoutParams lp = leftMenu.getLayoutParams();
				if(event.getX() < leftX){
					    lp.width = leftMenu.getWidth() - 14;
					    leftMenu.setLayoutParams(lp);
				} else {
					    lp.width = leftMenu.getWidth() + 14;
					    leftMenu.setLayoutParams(lp);
				}
				
				leftX = event.getX();
				
				return true;
			}
		});
		
		rigthSc.setOnTouchListener(new View.OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event){
				LayoutParams lp = rightMenu.getLayoutParams();
				if(event.getX() > rigthX){
					    lp.width = rightMenu.getWidth() - 14;
					    rightMenu.setLayoutParams(lp);
				} else {
					    lp.width= rightMenu.getWidth() + 14;
					    rightMenu.setLayoutParams(lp);
				}
				
				rigthX = event.getX();
				
				return true;
			}
		});
		proje = new HashMap<>();
		settings = getIntent().getStringExtra("path").concat("/system/settings.pre");
		proje = new Gson().fromJson(FileUtil.readFile(settings), new TypeToken<HashMap<String, Object>>(){}.getType());
		defaultScenePath = proje.get("defaultScene").toString();
		json = FileUtil.readFile(getIntent().getStringExtra("path").concat(defaultScenePath));
		try {
			jo = new JSONObject(json);
			for(int i = 0; i < jo.getJSONArray("Objects").length(); i++){
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("file", "obj");
					list1.add(_item);
				}
				
			}
		} catch (Exception e) {
		}
		recyclerview2.setAdapter(new Recyclerview2Adapter(list1));
		recyclerview2.setLayoutManager(new LinearLayoutManager(this));
		linear59.addView(new Game().getView(this));
		tmr = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						//listview1.getLayoutParams().height = (int) listHeight;
						if (isObject) {
							comps.setVisibility(View.VISIBLE);
							insp.setVisibility(View.GONE);
						}
						else {
							comps.setVisibility(View.GONE);
							insp.setVisibility(View.VISIBLE);
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(tmr, (int)(0), (int)(100));
		listview.setVerticalScrollBarEnabled(false);
		RewardedAd.load(.this, _reward_ad_unit_id, new AdRequest.Builder().build(), __rewarded_ad_load_callback);
		 .show(.this, __on_user_earned_reward_listener);
		if ( != null) {
			.setFullScreenContentCallback(__full_screen_content_callback);
		} else {
			SketchwareUtil.showMessage(getApplicationContext(), "Error: RewardedAd  hasn't been loaded yet!");
		}
	}
	
	public void _Extra() {
	}
	
	private TreeViewList.TreeViewAdapter adapter;
		private List<TreeViewList.TreeNode> nodes2;
		private TreeViewList.TreeNode<TreeViewList.Dir> node;
		
		 public static View view;
			
								public static void setView(View v){
					                    view = v;
					             }
		
		public void loadFilesTotree(String path, final RecyclerView recycler, String rootFolderName){
				
						TreeViewList.isPath = true;
				     //TreeViewList.backgroundColor = 0xFF000000;
				     //TreeViewList.textColor = 0xFFFFFFFF;
						recycler.setBackgroundColor(TreeViewList.backgroundColor);
				
				
						nodes2 = new ArrayList<>();
						node = new TreeViewList.TreeNode<>(new TreeViewList.Dir(rootFolderName));
						nodes2.add(node);
							
						 
						initData2(path, node);
							
								
								recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
				
						adapter = new TreeViewList.TreeViewAdapter(nodes2, Arrays.asList(new TreeViewList.FileNodeBinder(), new TreeViewList.DirectoryNodeBinder()));
						// whether collapse child nodes when their parent node was close.
				//        adapter.ifCollapseChildWhileCollapseParent(true);
						adapter.setOnTreeNodeListener(new TreeViewList.TreeViewAdapter.OnTreeNodeListener() {
										@Override
										public boolean onClick(String clickedPath, TreeViewList.TreeNode node, RecyclerView.ViewHolder holder) {
														if (!node.isLeaf()) {
																		//Update and toggle the node.
																		onToggle(!node.isExpand(), holder);
													//                    if (!node.isExpand())
													//                        adapter.collapseBrotherNode(node);
														}
														
										// simple click
										if (FileUtil.isFile(clickedPath)) {
													_DrawerFileOnClick(clickedPath);
					
										}
										else {
													if (FileUtil.isDirectory(clickedPath)) {
																_DrawerFolderOnClick(clickedPath);
						
													}
										}
										
										
														return false;
										}
							
										@Override
										public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {
														TreeViewList.DirectoryNodeBinder.ViewHolder dirViewHolder = (TreeViewList.DirectoryNodeBinder.ViewHolder) holder;
														final ImageView ivArrow = dirViewHolder.getIvArrow();
														int rotateDegree = isExpand ? 90 : -90;
														ivArrow.animate().rotationBy(rotateDegree)
																.start();
										}
										
										@Override
										public void onLongClick(String clickedPath){
										
													//	Toast.makeText(getApplicationContext(), "long clicked : "+ clickedPath, Toast.LENGTH_SHORT).show();
										if (FileUtil.isFile(clickedPath)) {
					
													_DrawerOnFileLongClick(clickedPath);
					
										}
										else {
													if (FileUtil.isDirectory(clickedPath)) {
						
																_DrawerOnFolderLongClicked(clickedPath,view);
													}
										}
										
										}
										
						});
						recycler.setAdapter(adapter);
								
								
				
				
						
				
						
		}
		
		public void initData2(String path, final TreeViewList.TreeNode<TreeViewList.Dir> dir){
				
				final String[] pathStr = {path};
				
				new Thread(new Runnable() {
								@Override
								public void run() {
												Looper.prepare();
														
										
												ArrayList<String> rootDir = new ArrayList<>();
										
												FileUtil.listDir(pathStr[0], rootDir);
												
												for(int i = 0; i < rootDir.size(); i++){
													    if (Uri.parse(rootDir.get((int)(i))).getLastPathSegment().equals("system")) {
														       rootDir.remove(i);
													     }
												}
												
												for (String one : rootDir){
																if (FileUtil.isFile(one)){
																				dir.addChild(new TreeViewList.TreeNode<>(new TreeViewList.File(one)));
																} else if (FileUtil.isDirectory(one)) {
																				TreeViewList.TreeNode<TreeViewList.Dir> dirTree = new TreeViewList.TreeNode<>(new TreeViewList.Dir(one));
																				dir.addChild(dirTree);
																				initData2(one, dirTree);
																}
												}
												
												
									}
				}).start();
				
						
		}
		
	
	
	
	public static class TreeViewList {
				
				    public static boolean isPath = false;
				    public static int textColor = 0xFF000000;
				    public static int backgroundColor = 0xFFFFFFFF;
				    public static boolean darkMode = false;
				
				
				    public static class TreeNode<T extends LayoutItemType> implements Cloneable {
							        private T content;
							        private TreeNode parent;
							        private List<TreeNode> childList;
							        private boolean isExpand;
							        private boolean isLocked;
							        //the tree high
							        private int height = UNDEFINE;
							
							        private static final int UNDEFINE = -1;
							
							        public TreeNode(@NonNull T content) {
										            this.content = content;
										            this.childList = new ArrayList<>();
										        }
							
							        public int getHeight() {
										            if (isRoot())
										                height = 0;
										            else if (height == UNDEFINE)
										                height = parent.getHeight() + 1;
										            return height;
										        }
							
							        public boolean isRoot() {
										            return parent == null;
										        }
							
							        public boolean isLeaf() {
										            return childList == null || childList.isEmpty();
										        }
							
							        public void setContent(T content) {
										            this.content = content;
										        }
							
							        public T getContent() {
										            return content;
										        }
							
							        public List<TreeNode> getChildList() {
										            return childList;
										        }
							
							        public void setChildList(List<TreeNode> childList) {
										            this.childList.clear();
										            for (TreeNode treeNode : childList) {
													                addChild(treeNode);
													            }
										        }
							
							        public TreeNode addChild(TreeNode node) {
										            if (childList == null)
										                childList = new ArrayList<>();
										            childList.add(node);
										            node.parent = this;
										            return this;
										        }
							
							        public boolean toggle() {
										            isExpand = !isExpand;
										            return isExpand;
										        }
							
							        public void collapse() {
										            if (isExpand) {
													                isExpand = false;
													            }
										        }
							
							        public void collapseAll() {
										            if (childList == null || childList.isEmpty()) {
													                return;
													            }
										            for (TreeNode child : this.childList) {
													                child.collapseAll();
													            }
										        }
							
							        public void expand() {
										            if (!isExpand) {
													                isExpand = true;
													            }
										        }
							
							        public void expandAll() {
										            expand();
										            if (childList == null || childList.isEmpty()) {
													                return;
													            }
										            for (TreeNode child : this.childList) {
													                child.expandAll();
													            }
										        }
							
							        public boolean isExpand() {
										            return isExpand;
										        }
							
							        public void setParent(TreeNode parent) {
										            this.parent = parent;
										        }
							
							        public TreeNode getParent() {
										            return parent;
										        }
							
							        public TreeNode<T> lock() {
										            isLocked = true;
										            return this;
										        }
							
							        public TreeNode<T> unlock() {
										            isLocked = false;
										            return this;
										        }
							
							        public boolean isLocked() {
										            return isLocked;
										        }
							
							        @Override
							        public String toString() {
										            return "TreeNode{" +
										                    "content=" + this.content +
										                    ", parent=" + (parent == null ? "null" : parent.getContent().toString()) +
										                    ", childList=" + (childList == null ? "null" : childList.toString()) +
										                    ", isExpand=" + isExpand +
										                    '}';
										        }
							
							        @Override
							        protected TreeNode<T> clone() throws CloneNotSupportedException {
										            TreeNode<T> clone = new TreeNode<>(this.content);
										            clone.isExpand = this.isExpand;
										            return clone;
										        }
							    }
				
				
				    //interface
				    public interface LayoutItemType {
							        int getLayoutId();
							    }
				
				
				    // Tree View Adapter
				
				
				    public static class TreeViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
							        private static final String KEY_IS_EXPAND = "IS_EXPAND";
							        private final List<? extends TreeViewBinder> viewBinders;
							        private List<TreeNode> displayNodes;
							        private int padding = 30;
							        private OnTreeNodeListener onTreeNodeListener;
							        private boolean toCollapseChild;
							
							        public TreeViewAdapter(List<? extends TreeViewBinder> viewBinders) {
										            this(null, viewBinders);
										        }
							
							        public TreeViewAdapter(List<TreeNode> nodes, List<? extends TreeViewBinder> viewBinders) {
										            displayNodes = new ArrayList<>();
										            if (nodes != null)
										                findDisplayNodes(nodes);
										            this.viewBinders = viewBinders;
										        }
							
							        private void findDisplayNodes(List<TreeNode> nodes) {
										            for (TreeNode node : nodes) {
													                displayNodes.add(node);
													                if (!node.isLeaf() && node.isExpand())
													                    findDisplayNodes(node.getChildList());
													            }
										        }
							
							        @Override
							        public int getItemViewType(int position) {
										            return displayNodes.get(position).getContent().getLayoutId();
										        }
							
							        @Override
							        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
										            View v = LayoutInflater.from(parent.getContext())
										                    .inflate(viewType, parent, false);
										            if (viewBinders.size() == 1)
										                return viewBinders.get(0).provideViewHolder(v);
										            for (TreeViewBinder viewBinder : viewBinders) {
													                if (viewBinder.getLayoutId() == viewType)
													                    return viewBinder.provideViewHolder(v);
													            }
										            return viewBinders.get(0).provideViewHolder(v);
										        }
							
							        @Override
							        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
										            if (payloads != null && !payloads.isEmpty()) {
													                Bundle b = (Bundle) payloads.get(0);
													                for (String key : b.keySet()) {
																                    switch (key) {
																			                        case KEY_IS_EXPAND:
																			                            if (onTreeNodeListener != null)
																			                                onTreeNodeListener.onToggle(b.getBoolean(key), holder);
																			                            break;
																			                    }
																                }
													            }
										            super.onBindViewHolder(holder, position, payloads);
										        }
							
							        @Override
							        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
										            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
													                holder.itemView.setPaddingRelative(displayNodes.get(position).getHeight() * padding, 3, 3, 3);
													            }else {
													                holder.itemView.setPadding(displayNodes.get(position).getHeight() * padding, 3, 3, 3);
													            }
										
										            final TextView txt = holder.itemView.findViewById(R.id.tv_name);
										
										            txt.setTextColor(textColor);
										            holder.itemView.setBackgroundColor(backgroundColor);
										
										            final String clickedPath[] = {""};
										
										            holder.itemView.setOnClickListener(new View.OnClickListener() {
													                @Override
													                public void onClick(View v) {
																                    TreeNode selectedNode = displayNodes.get(holder.getLayoutPosition());
																                    // Prevent multi-click during the short interval.
																                    try {
																			                        long lastClickTime = (long) holder.itemView.getTag();
																			                       if (System.currentTimeMillis() - lastClickTime < 500)
																			                            return;
																			                    } catch (Exception e) {
																			                        holder.itemView.setTag(System.currentTimeMillis());
																			                    }
																                    holder.itemView.setTag(System.currentTimeMillis());
																
																                    
																
																
																
																                    try {
																			                        Dir dirNode = (Dir) selectedNode.getContent();
																			                        clickedPath[0] = dirNode.dirName;
																			                    } catch (Exception e){
																			                        File fileNode = (File) selectedNode.getContent();
																			                        clickedPath[0] = fileNode.fileName;
																			                    }
																
																                    if (onTreeNodeListener != null && onTreeNodeListener.onClick(clickedPath[0],
																                            selectedNode, holder))
																                        return;
																                    if (selectedNode.isLeaf())
																                        return;
																                    // This TreeNode was locked to click.
																                    if (selectedNode.isLocked()) return;
																                    boolean isExpand = selectedNode.isExpand();
																                    int positionStart = displayNodes.indexOf(selectedNode) + 1;
																                    if (!isExpand) {
																			                        notifyItemRangeInserted(positionStart, addChildNodes(selectedNode, positionStart));
																			                    } else {
																			                        notifyItemRangeRemoved(positionStart, removeChildNodes(selectedNode, true));
																			                    }
																                }
													            });
													
													
													holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
													                @Override
													                public boolean onLongClick(View v) {
																                    TreeNode selectedNode = displayNodes.get(holder.getLayoutPosition());
																
																                    try {
																			                        Dir dirNode = (Dir) selectedNode.getContent();
																			                        clickedPath[0] = dirNode.dirName;
																			                    } catch (Exception e){
																			                        File fileNode = (File) selectedNode.getContent();
																			                        clickedPath[0] = fileNode.fileName;
																			                    }
																
																                   setView(v); 
																                   onTreeNodeListener.onLongClick(clickedPath[0]);
																
																
																                    return true;
																                }
													            });
													
													
										            for (TreeViewBinder viewBinder : viewBinders) {
													                if (viewBinder.getLayoutId() == displayNodes.get(position).getContent().getLayoutId())
													                    viewBinder.bindView(holder, position, displayNodes.get(position));
													            }
										        }
							
							        private int addChildNodes(TreeNode pNode, int startIndex) {
										            List<TreeNode> childList = pNode.getChildList();
										            int addChildCount = 0;
										            for (TreeNode treeNode : childList) {
													                displayNodes.add(startIndex + addChildCount++, treeNode);
													                if (treeNode.isExpand()) {
																                    addChildCount += addChildNodes(treeNode, startIndex + addChildCount);
																                }
													            }
										            if (!pNode.isExpand())
										                pNode.toggle();
										            return addChildCount;
										        }
							
							        private int removeChildNodes(TreeNode pNode) {
										            return removeChildNodes(pNode, true);
										        }
							
							        private int removeChildNodes(TreeNode pNode, boolean shouldToggle) {
										            if (pNode.isLeaf())
										                return 0;
										            List<TreeNode> childList = pNode.getChildList();
										            int removeChildCount = childList.size();
										            displayNodes.removeAll(childList);
										            for (TreeNode child : childList) {
													                if (child.isExpand()) {
																                    if (toCollapseChild)
																                        child.toggle();
																                    removeChildCount += removeChildNodes(child, false);
																                }
													            }
										            if (shouldToggle)
										                pNode.toggle();
										            return removeChildCount;
										        }
							
							        @Override
							        public int getItemCount() {
										            return displayNodes == null ? 0 : displayNodes.size();
										        }
							
							        public void setPadding(int padding) {
										            this.padding = padding;
										        }
							
							        public void ifCollapseChildWhileCollapseParent(boolean toCollapseChild) {
										            this.toCollapseChild = toCollapseChild;
										        }
							
							        public void setOnTreeNodeListener(OnTreeNodeListener onTreeNodeListener) {
										            this.onTreeNodeListener = onTreeNodeListener;
										        }
							
							        public interface OnTreeNodeListener {
										            /**
             * called when TreeNodes were clicked.
             * @return weather consume the click event.
             */
										            boolean onClick(String clickedPath, TreeNode node, RecyclerView.ViewHolder holder);
										
										            /**
             * called when TreeNodes were toggle.
             * @param isExpand the status of TreeNodes after being toggled.
             */
										            void onToggle(boolean isExpand, RecyclerView.ViewHolder holder);
													
													
													//long clickedPath
													void onLongClick(String clickedPath);
										        }
							
							        public void refresh(List<TreeNode> treeNodes) {
										            displayNodes.clear();
										            findDisplayNodes(treeNodes);
										            notifyDataSetChanged();
										        }
							
							        public Iterator<TreeNode> getDisplayNodesIterator() {
										            return displayNodes.iterator();
										        }
							
							        private void notifyDiff(final List<TreeNode> temp) {
										            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
													                @Override
													                public int getOldListSize() {
																                    return temp.size();
																                }
													
													                @Override
													                public int getNewListSize() {
																                    return displayNodes.size();
																                }
													
													                // judge if the same items
													                @Override
													                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
																                    return TreeViewAdapter.this.areItemsTheSame(temp.get(oldItemPosition), displayNodes.get(newItemPosition));
																                }
													
													                // if they are the same items, whether the contents has bean changed.
													                @Override
													                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
																                    return TreeViewAdapter.this.areContentsTheSame(temp.get(oldItemPosition), displayNodes.get(newItemPosition));
																                }
													
													                @Nullable
													                @Override
													                public Object getChangePayload(int oldItemPosition, int newItemPosition) {
																                    return TreeViewAdapter.this.getChangePayload(temp.get(oldItemPosition), displayNodes.get(newItemPosition));
																                }
													            });
										            diffResult.dispatchUpdatesTo(this);
										        }
							
							        private Object getChangePayload(TreeNode oldNode, TreeNode newNode) {
										            Bundle diffBundle = new Bundle();
										            if (newNode.isExpand() != oldNode.isExpand()) {
													                diffBundle.putBoolean(KEY_IS_EXPAND, newNode.isExpand());
													            }
										            if (diffBundle.size() == 0)
										                return null;
										            return diffBundle;
										        }
							
							        // For DiffUtil, if they are the same items, whether the contents has bean changed.
							        private boolean areContentsTheSame(TreeNode oldNode, TreeNode newNode) {
										            return oldNode.getContent() != null && oldNode.getContent().equals(newNode.getContent())
										                    && oldNode.isExpand() == newNode.isExpand();
										        }
							
							        // judge if the same item for DiffUtil
							        private boolean areItemsTheSame(TreeNode oldNode, TreeNode newNode) {
										            return oldNode.getContent() != null && oldNode.getContent().equals(newNode.getContent());
										        }
							
							        /**
         * collapse all root nodes.
         */
							        public void collapseAll() {
										            // Back up the nodes are displaying.
										            List<TreeNode> temp = backupDisplayNodes();
										            //find all root nodes.
										            List<TreeNode> roots = new ArrayList<>();
										            for (TreeNode displayNode : displayNodes) {
													                if (displayNode.isRoot())
													                    roots.add(displayNode);
													            }
										            //Close all root nodes.
										            for (TreeNode root : roots) {
													                if (root.isExpand())
													                    removeChildNodes(root);
													            }
										            notifyDiff(temp);
										        }
							
							        @NonNull
							        private List<TreeNode> backupDisplayNodes() {
										            List<TreeNode> temp = new ArrayList<>();
										            for (TreeNode displayNode : displayNodes) {
													                try {
																                    temp.add(displayNode.clone());
																                } catch (CloneNotSupportedException e) {
																                    temp.add(displayNode);
																                }
													            }
										            return temp;
										        }
							
							        public void collapseNode(TreeNode pNode) {
										            List<TreeNode> temp = backupDisplayNodes();
										            removeChildNodes(pNode);
										            notifyDiff(temp);
										        }
							
							        public void collapseBrotherNode(TreeNode pNode) {
										            List<TreeNode> temp = backupDisplayNodes();
										            if (pNode.isRoot()) {
													                List<TreeNode> roots = new ArrayList<>();
													                for (TreeNode displayNode : displayNodes) {
																                    if (displayNode.isRoot())
																                        roots.add(displayNode);
																                }
													                //Close all root nodes.
													                for (TreeNode root : roots) {
																                    if (root.isExpand() && !root.equals(pNode))
																                        removeChildNodes(root);
																                }
													            } else {
													                TreeNode parent = pNode.getParent();
													                if (parent == null)
													                    return;
													                List<TreeNode> childList = parent.getChildList();
													                for (TreeNode node : childList) {
																                    if (node.equals(pNode) || !node.isExpand())
																                        continue;
																                    removeChildNodes(node);
																                }
													            }
										            notifyDiff(temp);
										        }
							
							    }
				
				
				    // Tree View Binder
				
				    public static abstract class TreeViewBinder<VH extends RecyclerView.ViewHolder> implements LayoutItemType {
							        public abstract VH provideViewHolder(View itemView);
							
							        public abstract void bindView(VH holder, int position, TreeNode node);
							
							        public static class ViewHolder extends RecyclerView.ViewHolder {
										            public ViewHolder(View rootView) {
													                super(rootView);
													            }
										
										            protected <T extends View> T findViewById(@IdRes int id) {
													                return (T) itemView.findViewById(id);
													            }
										        }
							
							    }
				
				
				    public static class FileNodeBinder extends TreeViewBinder<FileNodeBinder.ViewHolder> {
							        @Override
							        public ViewHolder provideViewHolder(View itemView) {
										            return new ViewHolder(itemView);
										        }
							
							        @Override
							        public void bindView(ViewHolder holder, int position, TreeNode node) {
										            File fileNode = (File) node.getContent();
										            if (TreeViewList.isPath) {
													                holder.tvName.setText(Uri.parse(fileNode.fileName).getLastPathSegment());
													            } else {
													                holder.tvName.setText(fileNode.fileName);
													            }
										        }
							
							        @Override
							        public int getLayoutId() {
										            return R.layout.item_file;
										        }
							
							        public class ViewHolder extends TreeViewBinder.ViewHolder {
										            public TextView tvName;
										
										            public ViewHolder(View rootView) {
													                super(rootView);
													                this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
													            }
										
										        }
							    }
				
				
				    public static class DirectoryNodeBinder extends TreeViewBinder<DirectoryNodeBinder.ViewHolder> {
							        @Override
							        public ViewHolder provideViewHolder(View itemView) {
										            return new ViewHolder(itemView);
										        }
							
							        @Override
							        public void bindView(ViewHolder holder, int position, TreeNode node) {
										            holder.ivArrow.setRotation(0);
										           if(TreeViewList.darkMode){
													       holder.ivArrow.setImageResource(R.drawable.arrow);
										}else{
													    holder.ivArrow.setImageResource(R.drawable.arrow1);
										}
										            int rotateDegree = node.isExpand() ? 90 : 0;
										            holder.ivArrow.setRotation(rotateDegree);
										            Dir dirNode = (Dir) node.getContent();
										
										            if (TreeViewList.isPath) {
													                holder.tvName.setText(Uri.parse(dirNode.dirName).getLastPathSegment());
													            } else {
													                holder.tvName.setText(dirNode.dirName);
													            }
										
										            if (node.isLeaf())
										                holder.ivArrow.setVisibility(View.INVISIBLE);
										            else holder.ivArrow.setVisibility(View.VISIBLE);
										        }
							
							        @Override
							        public int getLayoutId() {
										            return R.layout.item_dir;
										        }
							
							        public static class ViewHolder extends TreeViewBinder.ViewHolder {
										            private ImageView ivArrow;
										            private TextView tvName;
										
										            public ViewHolder(View rootView) {
													                super(rootView);
													                this.ivArrow = (ImageView) rootView.findViewById(R.id.iv_arrow);
													                this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
													            }
										
										            public ImageView getIvArrow() {
													                return ivArrow;
													            }
										
										            public TextView getTvName() {
													                return tvName;
													            }
										        }
							    }
				
				
				    public static class Dir implements TreeViewList.LayoutItemType {
							        public String dirName;
							
							        public Dir(String dirName) {
										            this.dirName = dirName;
										        }
							
							        @Override
							        public int getLayoutId() {
										            return R.layout.item_dir;
										        }
							    }
				
				
				    public static class File implements TreeViewList.LayoutItemType {
							        public String fileName;
							
							        public File(String fileName) {
										            this.fileName = fileName;
										        }
							
							        @Override
							        public int getLayoutId() {
										            return R.layout.item_file;
										        }
							    }
	}
	
	
	{
	}
	
	
	public void _DrawerFolderOnClick(final String _path) {
		File_map.clear();
		FileUtil.listDir(_path, liststring);
		Collections.sort(liststring, String.CASE_INSENSITIVE_ORDER);
		position = 0;
		for(int _repeat15 = 0; _repeat15 < (int)(liststring.size()); _repeat15++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("file", liststring.get((int)(position)));
				File_map.add(_item);
			}
			
			position++;
		}
		gridview1.setNumColumns((int)4);
		gridview1.setAdapter(new Gridview1Adapter(File_map));
	}
	
	
	public void _DrawerFileOnClick(final String _path) {
		
	}
	
	
	public void _DrawerOnFileLongClick(final String _path) {
		
	}
	
	
	public void _DrawerOnFolderLongClicked(final String _path, final View _view) {
		final PopupWindow pw = new PopupWindow(EditorActivity.this);
					View v = getLayoutInflater().inflate(R.layout.create_f,null);
		pw.setFocusable(true);
					pw.setContentView(v);
		
		LinearLayout lua = v.findViewById(R.id.lscript);
		LinearLayout folder = v.findViewById(R.id.folder);
		LinearLayout js = v.findViewById(R.id.js);
		LinearLayout ui = v.findViewById(R.id.ui);
		
					pw.showAsDropDown(_view);
		lua.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_EditTextDialog("LuaScript", "Create the LuaScript", _path, "lua", "empty = GameObject:findObjectByName(\"EmptyName\")\n\nfunction onFunctionInit()\n\nend\n\nfunction onFunctionBegin()\n     empty:setPosition(0,0)\n     empty:setRotation(0,0,0)\n     empty:setScale(2,2)\nend\n\nfunction onClick()\n\nend", "newLua");
			}
		});
		folder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final EditText text = new EditText(EditorActivity.this);
				LinearLayout.LayoutParams _Mk = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				text.setLayoutParams(_Mk);
				dialog.setView(text);
				dialog.setTitle("Create Folder");
				text.setHint("Folder Name");
				dialog.setMessage("please write the folder name you want");
				dialog.setPositiveButton("Create", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						FileUtil.writeFile(_path.concat("/".concat(text.getText().toString().concat("/"))), "");
						pw.dismiss();
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
		js.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_EditTextDialog("JavaScript", "Create the JavaScript", _path, "js", "Object.toast(\"deneme\");\nvar hero = Object.findObjectByName(\"hero\");\nhero.setScale(2,2)", "newJS");
			}
		});
		ui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_EditTextDialog("GUI", "Create the UI Widget", _path, "gui", "", "uiWidget");
			}
		});
	}
	
	
	public void _GameVoid() {
	}
	
	float[] combineds = new float[16];
	float[] views = new float[16];
	
	class Game extends Level {
		     @Override
				public void onCreate() {
						_create();
				}
		
				@Override
				public void onDraw() {
						_render();
			       combineds = combined;
			       views = view;
				}
		
		     @Override
		     public void onClickScreen(){}
	}
	
	{
	}
	
	
	public void _create() {
		
	}
	
	
	public void _render() {
		if(jo != null){
		}
		json = FileUtil.readFile(getIntent().getStringExtra("path").concat(defaultScenePath));
		 try {
			    for(int i = 0; i < jo.getJSONArray("Objects").length(); i++){
				        Empty empty = new Empty();
				for(int ci = 0; ci < jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").length(); ci++){
					if(jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").getJSONObject(ci).getString("type").equals("Square")){
						Square sq = new Square();
						empty.addComponent(sq);
					}
					if(jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").getJSONObject(ci).getString("type").equals("Text")){
						Text tx = new Text();
						tx.setText(jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").getJSONObject(ci).getString("text").toString());
						empty.addComponent(tx);
					}
				}
				empty.setRotation(Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(i).getString("rX")), Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(i).getString("rY")),Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(i).getString("rZ")));
				
				empty.setColor(Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(i).getString("cR")), Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(i).getString("cG")),Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(i).getString("cB")), Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(i).getString("cA")));
				
				if(jo.getJSONArray("Objects").getJSONObject(i).getString("texture").equals("null")){
					
				} else {
					empty.setTexture(jo.getJSONArray("Objects").getJSONObject(i).getString("texture"));
				}
				empty.setPosition(Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(i).getString("pX")), Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(i).getString("pY")));
				
				empty.setScale(Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(i).getString("sX")), Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(i).getString("sY")));
				        empty.initComponents();
				        empty.draw(combineds, views);
			}
		} catch (Exception e) {
			    
		}
	}
	
	
	public void _setJsonEditTexts() {
		compList.clear();
		name.setText(names);
		try {
			for(int i = 0; i < jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").length(); i++){
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("file", "comp");
					compList.add(_item);
				}
				
			}
		} catch (Exception e) {}
		
		
		listview.setVisibility(View.VISIBLE);
		listview.setAdapter(new ListviewAdapter(compList));
		((BaseAdapter)listview.getAdapter()).notifyDataSetChanged();
	}
	
	
	public void _sui_option_button(final View _view, final String _tooltip) {
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
							        .on(EditorActivity.this, _view)
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
	
	
	public void _setObjectList() {
		recyclerview2.setAdapter(new Recyclerview2Adapter(list1));
		recyclerview2.setLayoutManager(new LinearLayoutManager(this));
	}
	
	
	public void _EditTextDialog(final String _title, final String _explanation, final String _file, final String _extension, final String _text, final String _hint) {
		dialog.setTitle(_title);
		final EditText text = new EditText(EditorActivity.this);
		LinearLayout.LayoutParams _Mk = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		text.setLayoutParams(_Mk);
		dialog.setView(text);
		text.setHint(_hint);
		dialog.setMessage(_explanation);
		dialog.setPositiveButton("Create", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				FileUtil.writeFile(_file.concat("/".concat(text.getText().toString().concat(".".concat(_extension)))), _text);
			}
		});
		dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		dialog.create().show();
	}
	
	
	public void _texturesListAdapter() {
	}
	public class ListviewTexturesAdapter extends BaseAdapter {
				
				ArrayList<HashMap<String, Object>> _data;
				
				public ListviewTexturesAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
								_view = _inflater.inflate(R.layout.texrure_item, null);
					}
			
			final TextView nameT = _view.findViewById(R.id.name);
			final ImageView logoT = _view.findViewById(R.id.logo);
			
			nameT.setText(Uri.parse(textureString.get((int)(_position))). getLastPathSegment());
			
			if(textureString.get((int)(_position)).endsWith("lua")){
				logoT.setImageResource(R.drawable.lua);
			}
			if(FileUtil.isDirectory(textureString.get((int)(_position)))){
				
				
				logoT.setImageResource(R.drawable.folder);
			}
			
			if(FileUtil.isFile(textureString.get((int)(_position)))){
				if(textureString.get((int)(_position)).endsWith(".png")|textureString.get((int)(_position)).endsWith(".jpg")|textureString.get((int)(_position)).endsWith(".jpeg")){
					logoT.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(textureString.get((int)(_position)), 1024, 1024));
				}
			}
			
					return _view;
			}
	}
	
	
	public void _scriptsListAdapter() {
	}
	public class ScriptsAdapter extends BaseAdapter {
					
					ArrayList<HashMap<String, Object>> _data;
					
					public ScriptsAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
											_view = _inflater.inflate(R.layout.texrure_item, null);
							}
					
					final TextView nameT = _view.findViewById(R.id.name);
					final ImageView logoT = _view.findViewById(R.id.logo);
					
					nameT.setText(Uri.parse(scriptString.get((int)(_position))). getLastPathSegment());
					
					if(scriptString.get((int)(_position)).endsWith("lua")){
							logoT.setImageResource(R.drawable.lua);
					}
					if(FileUtil.isDirectory(scriptString.get((int)(_position)))){
							
							
							logoT.setImageResource(R.drawable.folder);
					}
					
					if(FileUtil.isFile(scriptString.get((int)(_position)))){
							if(scriptString.get((int)(_position)).endsWith(".png")|scriptString.get((int)(_position)).endsWith(".jpg")|scriptString.get((int)(_position)).endsWith(".jpeg")){
									logoT.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(scriptString.get((int)(_position)), 1024, 1024));
							}
					}
					
							return _view;
				}
	}
	
	
	public void _colorAll1() {
		try{
			jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("cR",1);
			jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("cG",1);
			jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("cB",1);
			jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("cA",1);
			
		}catch(Exception e){
			 
		}
	}
	
	
	public void _clickEdittext(final TextView _view, final String _var) {
		dialog.setTitle("Set ".concat(_var));
		final EditText text = new EditText(EditorActivity.this);
		LinearLayout.LayoutParams _Mk = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		text.setLayoutParams(_Mk);
		dialog.setView(text);
		text.setText(_view.getText().toString());
		dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				_view.setText(text.getText().toString());
				try { 
					if(_view.getText().toString().equals("")){
					} else {
						jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put(_var,_view.getText().toString());
					} 
				} catch (Exception e) { }
			}
		});
		dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		dialog.create().show();
	}
	
	public class Recyclerview2Adapter extends RecyclerView.Adapter<Recyclerview2Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.editor_recyler2, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			
			try {
				for(int i = 0; i < jo.getJSONArray("Objects").length(); i++){
					
				}
				
				textview1.setText(jo.getJSONArray("Objects").getJSONObject(_position).getString("name"));
				
			} catch (Exception e) {}
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					isObject = true;
					pos = String.valueOf(_position);
					try {
						names = jo.getJSONArray("Objects").getJSONObject(_position).getString("name");
						texture = jo.getJSONArray("Objects").getJSONObject(_position).getString("texture");
						cR = jo.getJSONArray("Objects").getJSONObject(_position).getString("cR");
						cG = jo.getJSONArray("Objects").getJSONObject(_position).getString("cG");
						cB = jo.getJSONArray("Objects").getJSONObject(_position).getString("cB");
						cA = jo.getJSONArray("Objects").getJSONObject(_position).getString("cA");
						
						pX = jo.getJSONArray("Objects").getJSONObject(_position).getString("pX");
						pY = jo.getJSONArray("Objects").getJSONObject(_position).getString("pY");
						sX = jo.getJSONArray("Objects").getJSONObject(_position).getString("sX");
						sY = jo.getJSONArray("Objects").getJSONObject(_position).getString("sY");
						rX = jo.getJSONArray("Objects").getJSONObject(_position).getString("rX");
						rY = jo.getJSONArray("Objects").getJSONObject(_position).getString("rY");
						rZ = jo.getJSONArray("Objects").getJSONObject(_position).getString("rZ");
						
					} catch (Exception e) {}
					squareCount=0;
					listHeight=0;
					try {
						for(int i = 0; i < jo.getJSONArray("Objects").getJSONObject(_position).getJSONArray("Components").length(); i++){
							if(jo.getJSONArray("Objects").getJSONObject(_position).getJSONArray("Components").getJSONObject(i).getString("type").equals("Square")){
								squareCount = squareCount + 1;
								listHeight = listHeight + (117 * squareCount);
							}
						}
					} catch (Exception e) {}
					_setJsonEditTexts();
				}
			});
			linear1.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					final PopupWindow pw = new PopupWindow(EditorActivity.this);
								View v = getLayoutInflater().inflate(R.layout.delete,null);
					pw.setFocusable(true);
								pw.setContentView(v);
					
					LinearLayout delete = v.findViewById(R.id.delete);
					LinearLayout copy = v.findViewById(R.id.copy);
					
								pw.showAsDropDown(_view);
					delete.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							try { 
								jo.getJSONArray("Objects").remove(_position);
							} catch (Exception e){}
							list1.clear();
							try { 
								for(int i = 0; i < jo.getJSONArray("Objects").length(); i++){
									{
										HashMap<String, Object> _item = new HashMap<>();
										_item.put("file", "obj");
										list1.add(_item);
									}
									
								}
							} catch (Exception e) {
							}
							_setObjectList();
							pw.dismiss();
							listview.setVisibility(View.GONE);
						}
					});
					copy.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							list1.clear();
							try {
								JSONObject empty = new JSONObject();
								    empty.put("name",names + "copy");
								    empty.put("visible","true");
								    empty.put("pX",pX);
								    empty.put("pY",pY);
								    empty.put("sX",sX);
								    empty.put("sY",sY);
								    empty.put("rX",rX);
								    empty.put("rY",rY);
								    empty.put("rZ",rZ);
								    empty.put("texture","null");
								    empty.put("cR",cR);
								    empty.put("cG",cG);
								    empty.put("cB",cB);
								    empty.put("cA",cA);
								    JSONArray compsj = new JSONArray();
								    
								    JSONObject sq = new JSONObject();
								    sq.put("type","Square");
								    
								    compsj.put(sq);
								    
								    empty.put("Components",compsj);
								jo.getJSONArray("Objects").put(empty);
							} catch (Exception e) {}
							try {
								for(int i = 0; i < jo.getJSONArray("Objects").length(); i++){
									{
										HashMap<String, Object> _item = new HashMap<>();
										_item.put("file", "obj");
										list1.add(_item);
									}
									
								}
							} catch (Exception e) {
							}
							recyclerview2.setAdapter(new Recyclerview2Adapter(list1));
							pw.dismiss();
						}
					});
					return true;
				}
			});
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}
	
	public class Gridview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Gridview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.editor_grid1, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView imageview3 = _view.findViewById(R.id.imageview3);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			
			file = Uri.parse(liststring.get((int)(_position))).getLastPathSegment();
			textview1.setText(file);
			if (FileUtil.isDirectory(liststring.get((int)(_position)))) {
				imageview3.setImageResource(R.drawable.folder);
				linear1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						_DrawerFolderOnClick(liststring.get((int)(_position)));
					}
				});
				linear1.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View _view) {
						_DrawerOnFolderLongClicked(liststring.get((int)(_position)), linear1);
						return true;
					}
				});
			}
			if (file.endsWith(".pre")) {
				imageview3.setImageResource(R.drawable.default_image);
			}
			if (file.endsWith(".jpg") || file.endsWith(".png")) {
				imageview3.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(liststring.get((int)(_position)), 1024, 1024));
			}
			if (file.endsWith(".pew")) {
				imageview3.setImageResource(R.drawable.icn_11);
			}
			if (file.endsWith(".lua")) {
				imageview3.setImageResource(R.drawable.lua);
			}
			if (file.endsWith(".js")) {
				imageview3.setImageResource(R.drawable.img_18);
			}
			if (file.endsWith(".gui")) {
				imageview3.setImageResource(R.drawable.widgets);
			}
			
			return _view;
		}
	}
	
	public class ListviewAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public ListviewAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.comps, null);
			}
			
			final ScrollView vscroll1 = _view.findViewById(R.id.vscroll1);
			final LinearLayout bg = _view.findViewById(R.id.bg);
			final LinearLayout transformComponent = _view.findViewById(R.id.transformComponent);
			final LinearLayout colorComponent = _view.findViewById(R.id.colorComponent);
			final LinearLayout apperanceComponent = _view.findViewById(R.id.apperanceComponent);
			final LinearLayout scriptComponent = _view.findViewById(R.id.scriptComponent);
			final LinearLayout SquareComp = _view.findViewById(R.id.SquareComp);
			final LinearLayout textComponent = _view.findViewById(R.id.textComponent);
			final LinearLayout linear37 = _view.findViewById(R.id.linear37);
			final LinearLayout transformContent = _view.findViewById(R.id.transformContent);
			final ImageView imageview5 = _view.findViewById(R.id.imageview5);
			final ImageView imageview17 = _view.findViewById(R.id.imageview17);
			final TextView textview18 = _view.findViewById(R.id.textview18);
			final LinearLayout linear38 = _view.findViewById(R.id.linear38);
			final LinearLayout linear39 = _view.findViewById(R.id.linear39);
			final LinearLayout linear40 = _view.findViewById(R.id.linear40);
			final LinearLayout linear41 = _view.findViewById(R.id.linear41);
			final LinearLayout linear77 = _view.findViewById(R.id.linear77);
			final LinearLayout linear78 = _view.findViewById(R.id.linear78);
			final LinearLayout linear79 = _view.findViewById(R.id.linear79);
			final TextView pxt = _view.findViewById(R.id.pxt);
			final EditText px = _view.findViewById(R.id.px);
			final TextView pyt = _view.findViewById(R.id.pyt);
			final EditText py = _view.findViewById(R.id.py);
			final TextView sxt = _view.findViewById(R.id.sxt);
			final EditText sx = _view.findViewById(R.id.sx);
			final TextView syt = _view.findViewById(R.id.syt);
			final EditText sy = _view.findViewById(R.id.sy);
			final TextView rxt = _view.findViewById(R.id.rxt);
			final EditText rx = _view.findViewById(R.id.rx);
			final TextView ryt = _view.findViewById(R.id.ryt);
			final EditText ry = _view.findViewById(R.id.ry);
			final TextView rzt = _view.findViewById(R.id.rzt);
			final EditText rz = _view.findViewById(R.id.rz);
			final LinearLayout linear35 = _view.findViewById(R.id.linear35);
			final LinearLayout colorContent = _view.findViewById(R.id.colorContent);
			final ImageView imageview4 = _view.findViewById(R.id.imageview4);
			final ImageView imageview16 = _view.findViewById(R.id.imageview16);
			final TextView textview8 = _view.findViewById(R.id.textview8);
			final LinearLayout linear27 = _view.findViewById(R.id.linear27);
			final LinearLayout linear28 = _view.findViewById(R.id.linear28);
			final LinearLayout linear29 = _view.findViewById(R.id.linear29);
			final LinearLayout linear30 = _view.findViewById(R.id.linear30);
			final TextView textview14 = _view.findViewById(R.id.textview14);
			final EditText cr = _view.findViewById(R.id.cr);
			final TextView textview15 = _view.findViewById(R.id.textview15);
			final EditText cg = _view.findViewById(R.id.cg);
			final TextView textview16 = _view.findViewById(R.id.textview16);
			final EditText cb = _view.findViewById(R.id.cb);
			final TextView textview17 = _view.findViewById(R.id.textview17);
			final EditText ca = _view.findViewById(R.id.ca);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout appearanceContent = _view.findViewById(R.id.appearanceContent);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			final ImageView imageview14 = _view.findViewById(R.id.imageview14);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final LinearLayout linear75 = _view.findViewById(R.id.linear75);
			final TextView textview5 = _view.findViewById(R.id.textview5);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout linear21 = _view.findViewById(R.id.linear21);
			final ImageView img = _view.findViewById(R.id.img);
			final TextView imgt = _view.findViewById(R.id.imgt);
			final ImageView imageview3 = _view.findViewById(R.id.imageview3);
			final LinearLayout linear46 = _view.findViewById(R.id.linear46);
			final LinearLayout scriptContent = _view.findViewById(R.id.scriptContent);
			final ImageView imageview10 = _view.findViewById(R.id.imageview10);
			final ImageView imageview20 = _view.findViewById(R.id.imageview20);
			final TextView textview33 = _view.findViewById(R.id.textview33);
			final TextView textview34 = _view.findViewById(R.id.textview34);
			final LinearLayout linear50 = _view.findViewById(R.id.linear50);
			final TextView scriptt = _view.findViewById(R.id.scriptt);
			final ImageView imageview11 = _view.findViewById(R.id.imageview11);
			final LinearLayout sqs = _view.findViewById(R.id.sqs);
			final LinearLayout sqc = _view.findViewById(R.id.sqc);
			final ImageView imageview24 = _view.findViewById(R.id.imageview24);
			final TextView Square = _view.findViewById(R.id.Square);
			final TextView textview36 = _view.findViewById(R.id.textview36);
			final LinearLayout linear52 = _view.findViewById(R.id.linear52);
			final LinearLayout textContent = _view.findViewById(R.id.textContent);
			final ImageView imageview12 = _view.findViewById(R.id.imageview12);
			final ImageView imageview19 = _view.findViewById(R.id.imageview19);
			final TextView textview28 = _view.findViewById(R.id.textview28);
			final LinearLayout linear53 = _view.findViewById(R.id.linear53);
			final LinearLayout linear54 = _view.findViewById(R.id.linear54);
			final TextView textview29 = _view.findViewById(R.id.textview29);
			final EditText textT = _view.findViewById(R.id.textT);
			final TextView textview31 = _view.findViewById(R.id.textview31);
			final LinearLayout linear55 = _view.findViewById(R.id.linear55);
			final TextView textview32 = _view.findViewById(R.id.textview32);
			final ImageView imageview13 = _view.findViewById(R.id.imageview13);
			
			final int posCo = _position;
			
			
			try {
				if(jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").getJSONObject(_position).getString("type").equals("Square")){ 
					SquareComp.addView(bg);
					bg.removeView(scriptComponent);
					bg.removeView(textComponent);
				}
			}
			catch (Exception e) {}
			px.setFocusable(true);
			
			try {
				if(jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").getJSONObject(_position).getString("type").equals("Script")){ 
					scriptComponent.addView(bg);
					bg.removeView(SquareComp);
					bg.removeView(textComponent);
				}
			}
			catch (Exception e) {}
			try {
				if(jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").getJSONObject(_position).getString("type").equals("Text")){ 
					textComponent.addView(bg);
					bg.removeView(scriptComponent);
					bg.removeView(SquareComp);
				}
			}
			catch (Exception e) {}
			textview36.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			px.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			py.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			sx.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			sy.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			cr.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			cg.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			cb.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			ca.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			linear21.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			compText.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			linear50.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			rx.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			ry.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			rz.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			textT.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)5, (int)1, Color.TRANSPARENT, 0xFF313131));
			sqs.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (isSquare) {
						imageview24.setRotation((float)(315));
						sqc.addView(SquareComp);
					}
					else {
						imageview24.setRotation((float)(270));
						SquareComp.removeView(sqc);
					}
					isSquare = !isSquare;
				}
			});
			linear37.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (isTransform) {
						imageview5.setRotation((float)(315));
						transformContent.addView(transformComponent);
					}
					else {
						imageview5.setRotation((float)(270));
						transformComponent.removeView(transformContent);
					}
					isTransform = !isTransform;
				}
			});
			linear35.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (isColor) {
						imageview4.setRotation((float)(315));
						colorContent.addView(colorComponent);
					}
					else {
						imageview4.setRotation((float)(270));
						colorComponent.removeView(colorContent);
					}
					isColor = !isColor;
				}
			});
			linear2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (isAppearance) {
						imageview2.setRotation((float)(315));
						appearanceContent.addView(apperanceComponent);
					}
					else {
						imageview2.setRotation((float)(270));
						apperanceComponent.removeView(appearanceContent);
					}
					isAppearance = !isAppearance;
				}
			});
			linear46.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (isScript) {
						imageview10.setRotation((float)(315));
						scriptContent.addView(scriptComponent);
					}
					else {
						imageview10.setRotation((float)(270));
						scriptComponent.removeView(scriptContent);
					}
					isScript = !isScript;
				}
			});
			linear52.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (isText) {
						imageview12.setRotation((float)(315));
						textContent.addView(textComponent);
					}
					else {
						imageview12.setRotation((float)(270));
						textComponent.removeView(textContent);
					}
					isText = !isText;
				}
			});
			
			pxt.setOnTouchListener(new OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
					float tx = Float.parseFloat(px.getText().toString());
					if(event.getX() < zpxt){
						tx = tx - 0.25f;
					} else {
						tx = tx + 0.25f;
					}
					zpxt = event.getX();
					px.setText(""+tx);
					
					try { 
						if(px.getText().toString().equals("")){
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("pX",0);
						} else {
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("pX",px.getText().toString());
						} 
					} catch (Exception e) { }
					
					return true;
				}
			});
			
			pyt.setOnTouchListener(new OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
					float tx = Float.parseFloat(py.getText().toString());
					if(event.getX() < zpxt){
						tx = tx - 0.25f;
					} else {
						tx = tx + 0.25f;
					}
					zpxt = event.getX();
					py.setText(""+tx);
					
					try { 
						if(py.getText().toString().equals("")){
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("pY",0);
						} else {
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("pY",py.getText().toString());
						} 
					} catch (Exception e) { }
					
					return true;
				}
			});
			
			sxt.setOnTouchListener(new OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
					float tx = Float.parseFloat(sx.getText().toString());
					if(event.getX() < zpxt){
						tx = tx - 0.25f;
					} else {
						tx = tx + 0.25f;
					}
					zpxt = event.getX();
					sx.setText(""+tx);
					
					try { 
						if(sx.getText().toString().equals("")){
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("sX",0);
						} else {
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("sX",sx.getText().toString());
						} 
					} catch (Exception e) { }
					
					return true;
				}
			});
			
			syt.setOnTouchListener(new OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
					float tx = Float.parseFloat(sy.getText().toString());
					if(event.getX() < zpxt){
						tx = tx - 0.25f;
					} else {
						tx = tx + 0.25f;
					}
					zpxt = event.getX();
					sy.setText(""+tx);
					
					try { 
						if(sy.getText().toString().equals("")){
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("sY",0);
						} else {
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("sY",sy.getText().toString());
						} 
					} catch (Exception e) { }
					
					return true;
				}
			});
			
			textview14.setOnTouchListener(new OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
					float tx = Float.parseFloat(cr.getText().toString());
					
					if(event.getX() < zpxt){
						tx = tx - 0.025f;
					} else {
						tx = tx + 0.025f;
					}
					
					if(tx <= 0.0f){
						tx = 0;
					}
					
					if(tx >= 1.0f){
						tx = 1;
					}
					
					zpxt = event.getX();
					cr.setText(""+tx);
					
					try { 
						if(cr.getText().toString().equals("")){
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("cR",0);
						} else {
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("cR",cr.getText().toString());
						} 
					} catch (Exception e) { }
					
					return true;
				}
			});
			
			textview15.setOnTouchListener(new OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
					float tx = Float.parseFloat(cg.getText().toString());
					if(event.getX() < zpxt){
						tx = tx - 0.025f;
					} else {
						tx = tx + 0.025f;
					}
					
					if(tx <= 0.0f){
						tx = 0;
					}
					
					if(tx >= 1.0f){
						tx = 1;
					}
					zpxt = event.getX();
					cg.setText(""+tx);
					
					try { 
						if(cg.getText().toString().equals("")){
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("cG",0);
						} else {
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("cG",cg.getText().toString());
						} 
					} catch (Exception e) { }
					
					return true;
				}
			});
			
			textview16.setOnTouchListener(new OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
					float tx = Float.parseFloat(cb.getText().toString());
					if(event.getX() < zpxt){
						tx = tx - 0.025f;
					} else {
						tx = tx + 0.025f;
					}
					
					if(tx <= 0.0f){
						tx = 0;
					}
					
					if(tx >= 1.0f){
						tx = 1;
					}
					zpxt = event.getX();
					cb.setText(""+tx);
					
					try { 
						if(cb.getText().toString().equals("")){
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("cB",0);
						} else {
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("cB",cb.getText().toString());
						} 
					} catch (Exception e) { }
					
					return true;
				}
			});
			
			textview17.setOnTouchListener(new OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
					float tx = Float.parseFloat(ca.getText().toString());
					if(event.getX() < zpxt){
						tx = tx - 0.025f;
					} else {
						tx = tx + 0.025f;
					}
					
					if(tx <= 0.0f){
						tx = 0;
					}
					
					if(tx >= 1.0f){
						tx = 1;
					}
					zpxt = event.getX();
					ca.setText(""+tx);
					
					try { 
						if(ca.getText().toString().equals("")){
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("cA",0);
						} else {
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("cA",ca.getText().toString());
						} 
					} catch (Exception e) { }
					
					return true;
				}
			});
			
			rxt.setOnTouchListener(new OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
					float tx = Float.parseFloat(rx.getText().toString());
					if(event.getX() < zpxt){
						tx = tx - 0.25f;
					} else {
						tx = tx + 0.25f;
					}
					zpxt = event.getX();
					rx.setText(""+tx);
					
					try { 
						if(rx.getText().toString().equals("")){
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("rX",0);
						} else {
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("rX",rx.getText().toString());
						} 
					} catch (Exception e) { }
					
					return true;
				}
			});
			
			ryt.setOnTouchListener(new OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
					float tx = Float.parseFloat(ry.getText().toString());
					if(event.getX() < zpxt){
						tx = tx - 0.25f;
					} else {
						tx = tx + 0.25f;
					}
					zpxt = event.getX();
					ry.setText(""+tx);
					
					try { 
						if(ry.getText().toString().equals("")){
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("rY",0);
						} else {
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("rY",ry.getText().toString());
						} 
					} catch (Exception e) { }
					
					return true;
				}
			});
			
			rzt.setOnTouchListener(new OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
					float tx = Float.parseFloat(rz.getText().toString());
					if(event.getX() < zpxt){
						tx = tx - 0.25f;
					} else {
						tx = tx + 0.25f;
					}
					zpxt = event.getX();
					rz.setText(""+tx);
					
					try { 
						if(rz.getText().toString().equals("")){
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("rZ",0);
						} else {
							jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("rZ",rz.getText().toString());
						} 
					} catch (Exception e) { }
					
					return true;
				}
			});
			compText.addTextChangedListener(new TextWatcher() {
							@Override
							public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
									final String _charSeq = _param1.toString();
									try { 
											jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").getJSONObject(_position).put("Text",compText.getText().toString());
									} catch (Exception e) { }
							}
							
							@Override
							public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
									
							}
							
							@Override
							public void afterTextChanged(Editable _param1) {
									
							}
					});
			px.setText(pX);
			py.setText(pY);
			sx.setText(sX);
			sy.setText(sY);
			cr.setText(cR);
			cg.setText(cG);
			cb.setText(cB);
			ca.setText(cA);
			rx.setText(rX);
			ry.setText(rY);
			rz.setText(rZ);
			try{
					img.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getString("texture"), 1024, 1024));
				
				imgt.setText(Uri.parse(jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getString("texture")).getLastPathSegment());
				
				scriptt.setText(Uri.parse(jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").getJSONObject((int)posCo).getString("path")).getLastPathSegment());
				
				textT.setText(jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").getJSONObject((int)_position).getString("text"));
				
			}catch(Exception e){
					 
			}
			linear21.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					final AlertDialog CustomDialog = new AlertDialog.Builder(EditorActivity.this).create();
							View inflate = getLayoutInflater().inflate(R.layout.file_choice,null); 
							CustomDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
							CustomDialog.setView(inflate);
					CustomDialog.show();
					
					final ListView lv = inflate.findViewById(R.id.listview1);
					final TextView ok = inflate.findViewById(R.id.ok);
					final TextView cancel = inflate.findViewById(R.id.cancel);
					final TextView clear = inflate.findViewById(R.id.clear);
					lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); lv.setItemsCanFocus(false);
					ok.setVisibility(View.GONE);
					textures.clear();
					FileUtil.listDir(getIntent().getStringExtra("path"), textureString);
					Collections.sort(textureString, String.CASE_INSENSITIVE_ORDER);
					posTexture = 0;
					for(int _repeat133 = 0; _repeat133 < (int)(textureString.size()); _repeat133++) {
						HashMap<String, Object> _item = new HashMap<>();
										_item.put("file", textureString.get((int)(_repeat133)));
										textures.add(_item);
					}
					lv.setAdapter(new ListviewTexturesAdapter(textures));
							((BaseAdapter)lv.getAdapter()).notifyDataSetChanged();
					
					lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
									@Override
									public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
											final int _position = _param3;
										   //posTexture = _position;
							
												
										
							posTexture = _position;
							if (FileUtil.isDirectory(textureString.get((int)(_position)))) {
								lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); lv.setItemsCanFocus(false);
								textures.clear();
								FileUtil.listDir(textureString.get((int)(_position)), textureString);
								Collections.sort(textureString, String.CASE_INSENSITIVE_ORDER);
								posTexture = 0;
								for(int _repeat178 = 0; _repeat178 < (int)(textureString.size()); _repeat178++) {
									HashMap<String, Object> _item = new HashMap<>();
													_item.put("file", textureString.get((int)(_repeat178)));
													textures.add(_item);
								}
								lv.setAdapter(new ListviewTexturesAdapter(textures));
										((BaseAdapter)lv.getAdapter()).notifyDataSetChanged();
							}
							else {
								
							}
							if (textureString.get((int)(_position)).endsWith("png") || (textureString.get((int)(_position)).endsWith("jpg") || textureString.get((int)(_position)).endsWith("jpeg"))) {
								ok.setVisibility(View.VISIBLE);
								ok.setOnClickListener(new View.OnClickListener() {
									@Override
									public void onClick(View _view) {
										try {
											   jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("texture", textureString.get(_position));
											
											img.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getString("texture"), 1024, 1024));
											_colorAll1();
											
											imgt.setText(Uri.parse(jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getString("texture")).getLastPathSegment());
											
										} catch (Exception e){}
										
										CustomDialog.dismiss();
									}
								});
							}
							else {
								    ok.setVisibility(View.GONE);
							}
							
							
						}
					});
					cancel.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							CustomDialog.dismiss();
						}
					});
					clear.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							try {
								   jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).put("texture", "null");
							} catch (Exception e){}
							
							CustomDialog.dismiss();
						}
					});
				}
			});
			linear50.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					final AlertDialog CustomDialog = new AlertDialog.Builder(EditorActivity.this).create();
							View inflate = getLayoutInflater().inflate(R.layout.file_choice,null); 
							CustomDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
							CustomDialog.setView(inflate);
					CustomDialog.show();
					
					final ListView lv = inflate.findViewById(R.id.listview1);
					final TextView ok = inflate.findViewById(R.id.ok);
					final TextView cancel = inflate.findViewById(R.id.cancel);
					final TextView clear = inflate.findViewById(R.id.clear);
					lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); lv.setItemsCanFocus(false);
					ok.setVisibility(View.GONE);
					scripts.clear();
					FileUtil.listDir(getIntent().getStringExtra("path"), scriptString);
					Collections.sort(textureString, String.CASE_INSENSITIVE_ORDER);
					posScript = 0;
					for(int _repeat302 = 0; _repeat302 < (int)(scriptString.size()); _repeat302++) {
						HashMap<String, Object> _item = new HashMap<>();
										_item.put("file", scriptString.get((int)(_repeat302)));
										scripts.add(_item);
					}
					lv.setAdapter(new ScriptsAdapter(scripts));
							((BaseAdapter)lv.getAdapter()).notifyDataSetChanged();
					
					lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
									@Override
									public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
											final int _position = _param3;
							
												
										
							posScript = _position;
							if (FileUtil.isDirectory(scriptString.get((int)(_position)))) {
								lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); lv.setItemsCanFocus(false);
								scripts.clear();
								FileUtil.listDir(scriptString.get((int)(_position)), scriptString);
								Collections.sort(scriptString, String.CASE_INSENSITIVE_ORDER);
								posScript = 0;
								for(int _repeat319 = 0; _repeat319 < (int)(scriptString.size()); _repeat319++) {
									HashMap<String, Object> _item = new HashMap<>();
													_item.put("file", scriptString.get((int)(_repeat319)));
													scripts.add(_item);
								}
								lv.setAdapter(new ScriptsAdapter(scripts));
										((BaseAdapter)lv.getAdapter()).notifyDataSetChanged();
							}
							else {
								if (scriptString.get((int)(_position)).endsWith("js") || scriptString.get((int)(_position)).endsWith("lua")) {
									ok.setVisibility(View.VISIBLE);
									ok.setOnClickListener(new View.OnClickListener() {
										@Override
										public void onClick(View _view) {
											try {
												
												jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").getJSONObject((int)posCo).put("path",scriptString.get(_position));
												
												scriptt.setText(Uri.parse(jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").getJSONObject((int)posCo).getString("path")).getLastPathSegment());
												
											} catch (Exception e){}
											
											CustomDialog.dismiss();
										}
									});
								}
								else {
									    ok.setVisibility(View.GONE);
								}
							}
							
							
							
						}
					});
					cancel.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							CustomDialog.dismiss();
						}
					});
					clear.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							try {
								   jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").getJSONObject((int)_position).put("path","null");
								
							} catch (Exception e){}
							
							CustomDialog.dismiss();
						}
					});
				}
			});
			px.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
							_clickEdittext(px, "pX");
					}
			});
			py.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
							_clickEdittext(py, "pY");
					}
			});
			sx.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
							_clickEdittext(sx, "sX");
					}
			});
			sy.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
							_clickEdittext(sy, "sY");
					}
			});
			rx.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
							_clickEdittext(rx, "rX");
					}
			});
			ry.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
							_clickEdittext(ry, "rY");
					}
			});
			rz.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
							_clickEdittext(rz, "rZ");
					}
			});
			cr.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
							_clickEdittext(cr, "cR");
					}
			});
			cg.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
							_clickEdittext(cg, "cG");
					}
			});
			cb.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
							_clickEdittext(cb, "cB");
					}
			});
			ca.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
							_clickEdittext(ca, "cA");
					}
			});
			String _tS = "";
			try { 
				   _tS = jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").getJSONObject((int)_position).getString("text");
			} catch (Exception e) { }
			textT.setText(_tS);
			textT.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					dialog.setTitle("Change Text");
					final EditText text = new EditText(EditorActivity.this);
					LinearLayout.LayoutParams _Mk = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
					text.setLayoutParams(_Mk);
					dialog.setView(text);
					text.setText(textT.getText().toString());
					dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							textT.setText(text.getText().toString());
							try { 
								if(textT.getText().toString().equals("")){
								} else {
									jo.getJSONArray("Objects").getJSONObject((int)Float.parseFloat(pos)).getJSONArray("Components").getJSONObject((int)_position).put("text",textT.getText().toString());
								} 
							} catch (Exception e) { }
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