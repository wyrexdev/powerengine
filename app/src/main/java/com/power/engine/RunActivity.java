package com.power.engine;

import android.animation.*;
import android.app.*;
import android.content.*;
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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import android.os.Environment;

public class RunActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	public JSONObject jo;
	private String json = "";
	public  float[] combineds = new float[16];
	public float[] views = new float[16];
	private String path = "";
	public Globals globals;
	private String log = "";
	private double px = 0;
	private double py = 0;
	private double sx = 0;
	private double sy = 0;
	private String textureLocation = "";
	private double rx = 0;
	private String jsPath = "";
	private double camZoom = 0;
	private boolean loop = false;
	public String jsCode;
	
	private LinearLayout linear1;
	private WebView webview1;
	private TextView textview1;
	
	private TimerTask tm;
	private TimerTask animationT;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.run);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		textview1 = findViewById(R.id.textview1);
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
	}
	
	private void initializeLogic() {
		loop = true;
		json = FileUtil.readFile(getIntent().getStringExtra("screenPath"));
		try {
			jo = new JSONObject(json);
		} catch (Exception e) { }
		setContentView(new Game().getView(this));
		path = getIntent().getStringExtra("screenPath");
		tm = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						textview1.setText(log);
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(tm, (int)(0), (int)(2000));
		getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		webview1.getSettings().setJavaScriptEnabled(true);
		
		
		
		
	}
	
	public void _Game() {
	}
	class Game extends Level {
		
		    @Override  
		    public void onCreate(){
			        _create();
			    }
		    
		    @Override
		    public void onDraw(){
			        camZoom = editorCam.zoom;
			        views = view;
			        combineds = combined;
			        run = true;
			        _render();
			    }
		    
		    @Override
		    public void onClickScreen(){
			        _onClickScreen();
			    }
		/*
 
*/
	}
	
	
	public void _create() {
		globals = standardGlobals();
	}
	
	
	public void _render() {
		
		try {
				    for(int i = 0; i < jo.getJSONArray("Objects").length(); i++){
						        Empty empty = new Empty();
						for(int ci = 0; ci < jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").length(); ci++){
								if(jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").getJSONObject(ci).getString("type").equals("Square")){
										Square sq = new Square();
										empty.addComponent(sq);
								}
						}
						for(int ci = 0; ci < jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").length(); ci++){
								if(jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").getJSONObject(ci).getString("type").equals("Text")){
										Text tx = new Text();
										tx.setText(jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").getJSONObject(ci).get("text").toString());
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
				    
				       for(int ci = 0; ci < jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").length(); ci++){
								if(jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").getJSONObject(ci).getString("type").equals("Square")){
										//Script sc = new Script();
										//empty.addComponent(sc);
								}
						}
					    
				        for(int ci = 0; ci < jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").length(); ci++){
										           if(jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").getJSONObject(ci).getString("type").equals("Script")){
												               
												               if (jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").getJSONObject(ci).getString("path").endsWith("lua")) {
													                   
													                   globals.load(FileUtil.readFile(jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").getJSONObject(ci).getString("path"))).call();
							                                           
							                                           if(loop){
								                                               globals.get("onFunctionInit").call();
								                                               loop = false;
								                                           }
							                                           
														           	   globals.get("onFunctionBegin").call();
														           	   
														           	   if(Input.isTouchScreen){
															           	       globals.get("onClick").call();
															           	   }
														           	   
														           } else {
															           jsCode = FileUtil.readFile(jo.getJSONArray("Objects").getJSONObject(i).getJSONArray("Components").getJSONObject(ci).getString("path"));
														           }
												               
												           }
										    }
								
				       empty.initComponents();
					   empty.draw(combineds, views);
					   
				      
				    
				      //globals.get("onFunctionRender").call();
				}
		} catch (Exception e){ log =  e.getMessage(); }
	}
	
	
	public void _findObject() {
	}
	
	public class GameObject {
				public EmptyLUA findObjectByName(String name){
							EmptyLUA empty = new EmptyLUA();
							try {
										for(int i = 0; i < jo.getJSONArray("Objects").length(); i++){
													if(jo.getJSONArray("Objects").getJSONObject(i).getString("name").equals(name)){
																empty.setIndex(i);
													}
										}
							} catch (Exception e){}
							
							return empty;
				}
	}
	
	public class EmptyLUA {
				public int wIndex = 0;
				
				public EmptyLUA(){
			            
				}
				
				public void setIndex(int index){
							wIndex = index;
				}
				
				public void setPosition(float x, float y){
							try {
										jo.getJSONArray("Objects").getJSONObject(wIndex).put("pX", x);
										jo.getJSONArray("Objects").getJSONObject(wIndex).put("pY", y);
							} catch (Exception e){}
				}
				
				public void setRotation(float x, float y, float z){
							try {
										jo.getJSONArray("Objects").getJSONObject(wIndex).put("rX", x);
										jo.getJSONArray("Objects").getJSONObject(wIndex).put("rY", y);
										jo.getJSONArray("Objects").getJSONObject(wIndex).put("rZ", y);
							} catch (Exception e){}
				}
				
				public void setScale(float x, float y){
							try {
										jo.getJSONArray("Objects").getJSONObject(wIndex).put("sX", x);
										jo.getJSONArray("Objects").getJSONObject(wIndex).put("sY", y);
							} catch (Exception e){}
				}
				/*    
public void setTexture(String location){
try {
textureLocation = getIntent().getStringExtra("path").concat(location);
jo.getJSONArray("Objects").getJSONObject(wIndex).put("texture", textureLocation);
} catch (Exception e){}
}*/
				public void setTexture(String location){
							try {
										jo.getJSONArray("Objects").getJSONObject(wIndex).put("texture", getIntent().getStringExtra("path").concat("content/").concat(location));
							} catch (Exception e){}
				}
				
				
				
				//GET    
				public double getPositionX() {
							try{
										px = Double.parseDouble(jo.getJSONArray("Objects").getJSONObject(wIndex).getString("pX"));
							} catch (Exception e){}
							return px;
				}
				
				public double getPositionY() {
							try{
										py = Double.parseDouble(jo.getJSONArray("Objects").getJSONObject(wIndex).getString("pY"));
							} catch (Exception e){}
							return py;
				}
				
				public double getScaleX() {
							try{
										sx = Double.parseDouble(jo.getJSONArray("Objects").getJSONObject(wIndex).getString("sX"));
							} catch (Exception e){}
							return sx;
				}
				
				public double getScaleY() {
							try{
										sy = Double.parseDouble(jo.getJSONArray("Objects").getJSONObject(wIndex).getString("sY"));
							} catch (Exception e){}
							return sy;
				}
				
				public String getTexture(){
							String loc = "null";
							try {
										loc = jo.getJSONArray("Objects").getJSONObject(wIndex).get("texture").toString();
							} catch (Exception e){}
							
							return loc;    
				}
				
				//public boolean t = false;
		        
			    boolean isObjectClicked;
		        
		        public boolean isClicked(){
			           isObjectClicked = Input.x/100 > this.getPositionX() - (this.getScaleX() / 2) && Input.x/100 < this.getPositionX() + (this.getScaleX() / 2) && Input.y/100 > this.getPositionY() - (this.getScaleY() / 2) && Input.y/100 < this.getPositionY() + (this.getScaleY() / 2);
			           return isObjectClicked;
			        }
			/*public boolean isTouched(){
		try {
		    if(Input.x < (Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(wIndex).get("pX").toString()) + (Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(wIndex).get("sX").toString())/2)*(SystemPE.width/100)) &&
		  	  Input.x > (Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(wIndex).get("pX").toString()) - (Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(wIndex).get("sX").toString())/2)*(SystemPE.width/100)) &&
		     Input.y < (Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(wIndex).get("pY").toString()) + (Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(wIndex).get("sY").toString())/2)*(SystemPE.width/100)) &&
			  Input.y > (Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(wIndex).get("pY").toString()) - (Float.parseFloat(jo.getJSONArray("Objects").getJSONObject(wIndex).get("sY").toString())/2)*(SystemPE.width/100))){
					t = true;
				
		} else {
			t = false;
		}
		} catch (Exception e){}
		return t;
	}*/
			
		    ComponentLUA cl = null;
			
		    public ComponentLUA GetComponent(String cmp){
			        cl = new ComponentLUA();
			        
			        cl.setObjectIndex(wIndex);
			        
			        if(cmp.equals("Text")){
				            try {
					                for(int i = 0; i < jo.getJSONArray("Objects").getJSONObject(wIndex).getJSONArray("Components").length(); i++){
						                if(jo.getJSONArray("Objects").getJSONObject(wIndex).getJSONArray("Components").getJSONObject(i).get("Type").toString().equals("Text")){
							                    cl.setComponentIndex(i);
							                }
						            }
					            } catch (Exception e){
					                
					            }
				        }
			        
			        return cl;
			    }
	}//emptylua end
	
	public class ComponentLUA {
		    public int objectIndex;
		    public int componentIndex;
		    
		    public void setComponentIndex(int i){
			        componentIndex = i;
			    }
		    
		    public void setObjectIndex(int i){
			        objectIndex = i;
			    }
		    
		    public void setText(String str){
			        try {
				            jo.getJSONArray("Objects").getJSONObject(objectIndex).getJSONArray("Components").getJSONObject(componentIndex).put("text",str);
				        } catch (Exception e) {}
			    }
	}
	
	public class TextLUA extends ComponentLUA {
		    
	}
	
	public class System {
				/*
public System(){

}

public void toast(String s) {
SketchwareUtil.showMessage(getApplicationContext(), s);
}

public void collision(EmptyLUA object1, EmptyLUA object2){
if (((object2.getPositionX() < object1.getPositionX() + object1.getScaleX() && object2.getPositionX() > object1.getPositionX()) || (object1.getPositionX() < object2.getPositionX() + object2.getScaleX() && object1.getPositionX() > object2.getPositionX())) && ((object2.getPositionY() < object1.getPositionY() + object1.getScaleY() && object2.getPositionY() > object1.getPositionY()) || (object1.getPositionY() < object2.getPositionY() + object2.getScaleY() && object1.getPositionY() > object2.getPositionY()))) {
this.toast("deneme");
}
}
*/
	}//system end
	
	class SaveGame {
		    
		    JSONObject jo;
			
			String fileN, path;
			
			public void initGame(String fileName){
					if(fileName.equals("")){
							fileName = "ProjectName";
					}
					
					path = Environment.getExternalStorageDirectory().getAbsolutePath()+("/" + fileName+".saveGame");
					
					writeFile(path,"");
					
			        try {
						    jo = new JSONObject(readFile(path));
					} catch (Exception e){
						    
					}
					
					fileN = fileName + ".saveGame";
			}
			
			public void saveString(String saveObject, String saveValue){
					if(saveObject.equals("")){
							saveObject = "null";
							saveValue = "value_null";
					}
					
					try {
							jo.put(saveObject,saveValue);
					} catch (Exception e){
							
					}
					
					writeFile(path, jo.toString());
			}
		    
			public String getString(String saveObject){
					String value = "";
					
					try {
							value = jo.getString(saveObject);
					} catch (Exception e){
							
					}
					
					return value;
			}
			
			private void createNewFile(String path) {
			        int lastSep = path.lastIndexOf(File.separator);
			        if (lastSep > 0) {
				            String dirPath = path.substring(0, lastSep);
				            makeDir(dirPath);
				        }
			
			        File file = new File(path);
			
			        try {
				            if (!file.exists()) file.createNewFile();
				        } catch (IOException e) {
				            e.printStackTrace();
				        }
			    }
		
		    public String readFile(String path) {
			        createNewFile(path);
			
			        StringBuilder sb = new StringBuilder();
			        FileReader fr = null;
			        try {
				            fr = new FileReader(new File(path));
				
				            char[] buff = new char[1024];
				            int length = 0;
				
				            while ((length = fr.read(buff)) > 0) {
					                sb.append(new String(buff, 0, length));
					            }
				        } catch (IOException e) {
				            e.printStackTrace();
				        } finally {
				            if (fr != null) {
					                try {
						                    fr.close();
						                } catch (Exception e) {
						                    e.printStackTrace();
						                }
					            }
				        }
			
			        return sb.toString();
			    }
		
		    public void writeFile(String path, String str) {
			        createNewFile(path);
			        FileWriter fileWriter = null;
			
			        try {
				            fileWriter = new FileWriter(new File(path), false);
				            fileWriter.write(str);
				            fileWriter.flush();
				        } catch (IOException e) {
				            e.printStackTrace();
				        } finally {
				            try {
					                if (fileWriter != null)
					                    fileWriter.close();
					            } catch (IOException e) {
					                e.printStackTrace();
					            }
				        }
			    }
			
			public void makeDir(String path) {
			        if (!isExistFile(path)) {
				            File file = new File(path);
				            file.mkdirs();
				        }
			    }
			
			public boolean isExistFile(String path) {
			        File file = new File(path);
			        return file.exists();
			    }
	}
	
	public Globals standardGlobals() {
				Globals jsg = JsePlatform.standardGlobals();
				jsg.set("GameObject",CoerceJavaToLua.coerce(new GameObject()));
				jsg.set("Empty",CoerceJavaToLua.coerce(new EmptyLUA()));
				jsg.set("Input", CoerceJavaToLua.coerce(new Input()));
				jsg.set("Text", CoerceJavaToLua.coerce(new TextLUA()));
				jsg.set("SaveGame", CoerceJavaToLua.coerce(new SaveGame()));
				jsg.set("Component", CoerceJavaToLua.coerce(new ComponentLUA()));
				//jsg.set("System",CoerceJavaToLua.coerce(new System()));
				return jsg;
				
	}
	
	
	public void _ji() {
		
		
		 @JavascriptInterface
		public EmptyJS findObjectByName(String s){
			    EmptyJS emptyj = new EmptyJS();
			    try {
				        for(int i = 0; i < jo.getJSONArray("Objects").length(); i++){
					            if(jo.getJSONArray("Objects").getJSONObject(i).getString("name").equals(s)){
						                emptyj.setIndex(i);
						            }
					        }
				    } catch (Exception e){}
			    
			    return emptyj;
		}
	}
	
	
	public void _emptyjs() {
	}
	
	public class EmptyJS {
		    public int sIndex = 0;
		    
		    public EmptyJS(){
			        
			    }
		    
		    
		    public void setIndex(int index){
			        sIndex = index;
			    }
		    
		    @JavascriptInterface
		    public void setPosition(float x, float y){
			        try {
				            jo.getJSONArray("Objects").getJSONObject(sIndex).put("pX", x);
				            jo.getJSONArray("Objects").getJSONObject(sIndex).put("pY", y);
				        } catch (Exception e){}
			    }
		
		    @JavascriptInterface    
		    public void setRotation(float x, float y, float z){
			            try {
				                jo.getJSONArray("Objects").getJSONObject(sIndex).put("rX", x);
				                jo.getJSONArray("Objects").getJSONObject(sIndex).put("rY", y);
				                jo.getJSONArray("Objects").getJSONObject(sIndex).put("rZ", y);
				            } catch (Exception e){}
			    }
		
		    @JavascriptInterface
		    public void setScale(float x, float y){
			            try {
				                jo.getJSONArray("Objects").getJSONObject(sIndex).put("sX", x);
				                jo.getJSONArray("Objects").getJSONObject(sIndex).put("sY", y);
				            } catch (Exception e){}
			    }
		
		    @JavascriptInterface    
		    public void setTexture(String location){
			            try {
				                jo.getJSONArray("Objects").getJSONObject(sIndex).put("texture", getIntent().getStringExtra("path").concat("content/").concat(location));
				            } catch (Exception e){}
			    }
		
		
		
		//GET
		
		@JavascriptInterface
		public void toast(String s) {
			SketchwareUtil.showMessage(getApplicationContext(), s);
		}    
		
		@JavascriptInterface
		public double getPositionX() {
			        try{
				            px = Double.parseDouble(jo.getJSONArray("Objects").getJSONObject(sIndex).getString("pX"));
				        } catch (Exception e){}
			        return px;
			    }
		
		@JavascriptInterface
		public double getPositionY() {
			        try{
				            py = Double.parseDouble(jo.getJSONArray("Objects").getJSONObject(sIndex).getString("pY"));
				        } catch (Exception e){}
			        return py;
			    }
		
		@JavascriptInterface
		public double getScaleX() {
			        try{
				            sx = Double.parseDouble(jo.getJSONArray("Objects").getJSONObject(sIndex).getString("sX"));
				        } catch (Exception e){}
			        return sx;
		}
		
		@JavascriptInterface
		public double getScaleY() {
			        try{
				            sy = Double.parseDouble(jo.getJSONArray("Objects").getJSONObject(sIndex).getString("sY"));
				        } catch (Exception e){}
			        return sy;
			    }
		
		@JavascriptInterface
		public double getRotationX() {
			        try{
				            rx = Double.parseDouble(jo.getJSONArray("Objects").getJSONObject(sIndex).getString("rX"));
				        } catch (Exception e){}
			        return rx;
			    }
		
		//emptylua end
		
	}
	
	
	public void _onClickScreen() {
		
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