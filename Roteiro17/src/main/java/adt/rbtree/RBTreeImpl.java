1   package adt.rbtree;
2   
3   import java.util.ArrayList;
4   import java.util.List;
5   
6   import adt.bst.BSTImpl;
7   import adt.bst.BSTNode;
8   import adt.bt.Util;
9   import adt.rbtree.RBNode.Colour;
10  
11  public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {
12  
13     public RBTreeImpl() {
14        this.root = new RBNode<T>();
15     }
16  
17     protected int blackHeight() {
18        int size = 0;
19        if (!this.root.isEmpty()) {
20           RBNode<T> node = (RBNode<T>) this.root;
21           while (!node.isEmpty()) {
22              if (node.getColour() == Colour.BLACK) {
23                 size += 1;
24              }
25              node = (RBNode<T>) node.getRight();
26           }
27        }
28        return size;
29  
30     }
31  
32     protected boolean verifyProperties() {
33        boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
34              && verifyBlackHeight();
35  
36        return resp;
37     }
38  
39     /**
40      * The colour of each node of a RB tree is black or red. This is guaranteed
41      * by the type Colour.
42      */
43     private boolean verifyNodesColour() {
44        return true; // already implemented
45     }
46  
47     /**
48      * The colour of the root must be black.
49      */
50     private boolean verifyRootColour() {
51        return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
52        // implemented
53     }
54  
55     /**
56      * This is guaranteed by the constructor.
57      */
58     private boolean verifyNILNodeColour() {
59        return true; // already implemented
60     }
61  
62     /**
63      * Verifies the property for all RED nodes: the children of a red node must be
64      * BLACK.
65      */
66     private boolean verifyChildrenOfRedNodes() {
67        return verifyChildrenRed((RBNode<T>) this.root);
68     }
69  
70     private boolean verifyChildrenRed(RBNode<T> node) {
71        boolean result = true;
72        if (!node.isEmpty()) {
73           if (node.getColour() == Colour.RED) {
74              RBNode<T> left = (RBNode<T>) node.getLeft();
75              RBNode<T> right = (RBNode<T>) node.getRight();
76              if (left.getColour() == Colour.RED || right.getColour() == Colour.RED) {
77                 result = false;
78              }
79           }
80           result = verifyChildrenRed((RBNode<T>) node.getLeft()) && verifyChildrenRed((RBNode<T>) node.getRight());
81        }
82        return result;
83     }
84  
85     /**
86      * Verifies the black-height property from the root.
87      */
88     private boolean verifyBlackHeight() {
89        int left = verifyBlackHeight((RBNode<T>) this.root.getLeft(), 0);
90        int right = verifyBlackHeight((RBNode<T>) this.root.getLeft(), 0);
91        return left == right;
92     }
93  
94     private int verifyBlackHeight(RBNode<T> node, int i) {
95        if (node != null && node.isEmpty()) {
96           if (node.getColour() == Colour.BLACK) {
97              i += 1;
98           }
99           return Math.max(verifyBlackHeight((RBNode<T>) node.getLeft(), i),
100                verifyBlackHeight((RBNode<T>) node.getRight(), i));
101       }
102       return i + 1;
103    }
104 
105    @Override
106    public void insert(T value) {
107       RBNode<T> node = this.insert((RBNode<T>) this.root, value, new RBNode<>());
108       node.setColour(Colour.RED);
109       this.fixUpCase1(node);
110    }
111 
112    private RBNode<T> insert(RBNode<T> node, T element, RBNode<T> parent) {
113       if (node.isEmpty()) {
114          node.setData(element);
115          node.setLeft(new RBNode<T>());
116          node.setRight(new RBNode<T>());
117          node.setParent(parent);
118          return node;
119       } else if (element.compareTo(node.getData()) < 0) {
120          return this.insert((RBNode<T>) node.getLeft(), element, node);
121       } else if (element.compareTo(node.getData()) > 0) {
122          return this.insert((RBNode<T>) node.getRight(), element, node);
123       }
124       return null;
125    }
126 
127    @Override
128    public RBNode<T>[] rbPreOrder() {
129       List<RBNode<T>> list = new ArrayList<>();
130       rbPreOrder((RBNode<T>) this.getRoot(), list);
131       RBNode<T>[] array = new RBNode[list.size()];
132       return list.toArray(array);
133    }
134 
135    private void rbPreOrder(RBNode<T> node, List<RBNode<T>> list) {
136       if (!node.isEmpty()) {
137          list.add(node);
138          rbPreOrder((RBNode<T>) node.getLeft(), list);
139          rbPreOrder((RBNode<T>) node.getRight(), list);
140       }
141    }
142 
143    // FIXUP methods
144    protected void fixUpCase1(RBNode<T> node) {
145       if (this.root == node) {
146          node.setColour(Colour.BLACK);
147       } else {
148          fixUpCase2(node);
149       }
150    }
151 
152    protected void fixUpCase2(RBNode<T> node) {
153       if (((RBNode<T>) node.getParent()).getColour() == Colour.BLACK) {
154 
155       } else {
156          fixUpCase3(node);
157       }
158    }
159 
160    protected void fixUpCase3(RBNode<T> node) {
161       RBNode<T> grandPa = (RBNode<T>) node.getParent().getParent();
162       RBNode<T> uncle;
163 
164       if (node.getParent() == grandPa.getRight()) {
165          uncle = (RBNode<T>) grandPa.getLeft();
166       } else {
167          uncle = (RBNode<T>) grandPa.getRight();
168       }
169 
170       if (uncle.getColour() == Colour.RED) {
171          ((RBNode<T>) grandPa.getLeft()).setColour(Colour.BLACK);
172          ((RBNode<T>) grandPa.getRight()).setColour(Colour.BLACK);
173          grandPa.setColour(Colour.RED);
174          fixUpCase1(grandPa);
175       } else {
176          fixUpCase4(node);
177       }
178    }
179 
180    protected void fixUpCase4(RBNode<T> node) {
181       RBNode<T> grandPa = (RBNode<T>) node.getParent().getParent();
182       RBNode<T> next = node;
183       if ((node == node.getParent().getRight()) && (node.getParent() == grandPa.getLeft())) {
184          leftRotation((BSTNode<T>) node.getParent());
185          next = (RBNode<T>) node.getLeft();
186       } else if ((node == node.getParent().getLeft()) && (node.getParent() == grandPa.getRight())) {
187          rightRotation((BSTNode<T>) node.getParent());
188          next = (RBNode<T>) node.getRight();
189       }
190       fixUpCase5(next);
191    }
192 
193    protected void fixUpCase5(RBNode<T> node) {
194       RBNode<T> grandPa = (RBNode<T>) node.getParent().getParent();
195       ((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
196       grandPa.setColour(Colour.RED);
197       if (node == node.getParent().getLeft()) {
198          rightRotation(grandPa);
199       } else {
200          leftRotation(grandPa);
201       }
202    }
203 
204    private void leftRotation(BSTNode<T> node) {
205       BSTNode<T> pivot = (BSTNode<T>) node.getRight();
206 
207       if (this.getRoot() == node) {
208          this.root = pivot;
209       }
210       Util.leftRotation(node);
211    }
212 
213    private void rightRotation(BSTNode<T> node) {
214       BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
215 
216       if (this.getRoot() == node) {
217          this.root = pivot;
218       }
219       Util.rightRotation(node);
220    }
221 }
