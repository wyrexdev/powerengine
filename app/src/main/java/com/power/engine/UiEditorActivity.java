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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
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

public class UiEditorActivity extends AppCompatActivity {
	
	private String jsonCode = "";
	public JSONObject joui;
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout rightMenu;
	private LinearLayout linear4;
	private LinearLayout leftMenu;
	private LinearLayout linear49;
	private HorizontalScrollView hscroll1;
	private ScrollView vscroll2;
	private LinearLayout linear8;
	private TextView textview1;
	private LinearLayout linear16;
	private TextView textview36;
	private ListView listview2;
	private LinearLayout leftSc;
	private LinearLayout linear57;
	private LinearLayout linear59;
	private LinearLayout linear67;
	private LinearLayout linear6;
	private ImageView imageview6;
	private LinearLayout rigthSc;
	private LinearLayout comps;
	private LinearLayout insp;
	private LinearLayout linear9;
	private LinearLayout linear63;
	private TextView textview38;
	private ScrollView vscroll4;
	private TextView textview2;
	private EditText name;
	private LinearLayout transformComponent;
	private ListView listview;
	private LinearLayout linear65;
	private TextView textview39;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.ui_editor);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		rightMenu = findViewById(R.id.rightMenu);
		linear4 = findViewById(R.id.linear4);
		leftMenu = findViewById(R.id.leftMenu);
		linear49 = findViewById(R.id.linear49);
		hscroll1 = findViewById(R.id.hscroll1);
		vscroll2 = findViewById(R.id.vscroll2);
		linear8 = findViewById(R.id.linear8);
		textview1 = findViewById(R.id.textview1);
		linear16 = findViewById(R.id.linear16);
		textview36 = findViewById(R.id.textview36);
		listview2 = findViewById(R.id.listview2);
		leftSc = findViewById(R.id.leftSc);
		linear57 = findViewById(R.id.linear57);
		linear59 = findViewById(R.id.linear59);
		linear67 = findViewById(R.id.linear67);
		linear6 = findViewById(R.id.linear6);
		imageview6 = findViewById(R.id.imageview6);
		rigthSc = findViewById(R.id.rigthSc);
		comps = findViewById(R.id.comps);
		insp = findViewById(R.id.insp);
		linear9 = findViewById(R.id.linear9);
		linear63 = findViewById(R.id.linear63);
		textview38 = findViewById(R.id.textview38);
		vscroll4 = findViewById(R.id.vscroll4);
		textview2 = findViewById(R.id.textview2);
		name = findViewById(R.id.name);
		transformComponent = findViewById(R.id.transformComponent);
		listview = findViewById(R.id.listview);
		linear65 = findViewById(R.id.linear65);
		textview39 = findViewById(R.id.textview39);
		
		textview36.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final PopupWindow pw = new PopupWindow(UiEditorActivity.this);
							View v = getLayoutInflater().inflate(R.layout.object_create,null);
				pw.setFocusable(true);
							pw.setContentView(v);
				
				LinearLayout empty = v.findViewById(R.id.empty);
				LinearLayout square = v.findViewById(R.id.square);
				LinearLayout text = v.findViewById(R.id.text);
				
				empty.setVisibility(View.GONE);
				text.setVisibility(View.GONE);
				
				//square.setText("Square UI");
				
				pw.showAsDropDown(textview36);
				square.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						try {
							    
							    joui.put("Square","");
						} catch (Exception e){}
					}
				});
			}
		});
	}
	
	private void initializeLogic() {
	}
	
	public void _Game() {
		
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