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
import android.widget.Button;
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
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
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

public class UploadFileActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	
	private String pathForDrawer = "";
	private String selectedFilePath = "";
	private String clickedPath = "";
	private boolean isDarkMode = false;
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private RecyclerView recyclerview1;
	private LinearLayout linear7;
	private LinearLayout property;
	private Button button3;
	private ImageView imageview1;
	private TextView textview1;
	private TextView textview2;
	private EditText edittext1;
	private LinearLayout linear6;
	private Button button2;
	private Button button1;
	
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.upload_file);
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
		linear5 = findViewById(R.id.linear5);
		recyclerview1 = findViewById(R.id.recyclerview1);
		linear7 = findViewById(R.id.linear7);
		property = findViewById(R.id.property);
		button3 = findViewById(R.id.button3);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		edittext1 = findViewById(R.id.edittext1);
		linear6 = findViewById(R.id.linear6);
		button2 = findViewById(R.id.button2);
		button1 = findViewById(R.id.button1);
		fp.setType("*/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (selectedFilePath.endsWith(".png") || selectedFilePath.endsWith(".jpg")) {
					FileUtil.copyFile(selectedFilePath, clickedPath.concat("/"));
					
				}
				if (selectedFilePath.endsWith(".lua")) {
					FileUtil.copyFile(selectedFilePath, clickedPath);
				}
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		button3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)0, Color.TRANSPARENT, 0xFF263238));
		TreeViewList.textColor = Color.WHITE;
		TreeViewList.backgroundColor = 0xFF424242;
		TreeViewList.darkMode = this.isDarkMode;
		pathForDrawer = getIntent().getStringExtra("path");
		loadFilesTotree(pathForDrawer, recyclerview1, Uri.parse(pathForDrawer).getLastPathSegment());
		edittext1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)0, Color.TRANSPARENT, 0xFF212121));
		textview1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)0, Color.TRANSPARENT, 0xFF212121));
		textview2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)0, Color.TRANSPARENT, 0xFF212121));
		property.setVisibility(View.GONE);
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
				selectedFilePath = _filePath.get((int)(0));
				property.setVisibility(View.VISIBLE);
				button3.setText("Re-Select");
				textview1.setText(Uri.parse(selectedFilePath).getLastPathSegment());
				java.io.File file = new java.io.File(selectedFilePath);
				Date lastModDate = new Date(file.lastModified());
				textview2.setText(lastModDate.toString());
				if (selectedFilePath.endsWith(".png") || selectedFilePath.endsWith(".jpg")) {
					imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(selectedFilePath, 1024, 1024));
				}
				if (selectedFilePath.endsWith(".pew")) {
					imageview1.setImageResource(R.drawable.icn_11);
				}
				if (selectedFilePath.endsWith(".mp3") || selectedFilePath.endsWith(".ogg")) {
					imageview1.setImageResource(R.drawable.default_image);
				}
				if (selectedFilePath.endsWith(".lua")) {
					imageview1.setImageResource(R.drawable.lua);
				}
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	public void _Extra() {
	}
	
	private TreeViewList.TreeViewAdapter adapter;
		private List<TreeViewList.TreeNode> nodes2;
		private TreeViewList.TreeNode<TreeViewList.Dir> node;
	
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
						_DrawerFolderOnLongClicked(clickedPath);
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
		clickedPath = _path;
		edittext1.setText(clickedPath.replace("/storage/emulated/0/Android/data/com.power.engine/files/", ""));
	}
	
	
	public void _DrawerFolderOnLongClicked(final String _path) {
		
	}
	
	
	public void _DrawerFileOnClick(final String _path) {
		
	}
	
	
	public void _DrawerOnFileLongClick(final String _path) {
		
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