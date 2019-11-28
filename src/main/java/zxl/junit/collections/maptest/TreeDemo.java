package zxl.junit.collections.maptest;


public class TreeDemo {
    public static void main(String[] args) {
        TreeDemo td = new TreeDemo();
        td.add(10);
        td.add(11);
        td.add(2);
        td.add(15);
        td.add(4);
        td.add(7);
        System.out.println("");
//        TreeNode
        System.out.println("frontOrderTraverse");
        TreeDemo.frontOrderTraverse(td.getT());

        System.out.println("middleOrderTraverse");
        TreeDemo.middleOrderTraverse(td.getT());

        System.out.println("laterOrderTraverse");
        TreeDemo.laterOrderTraverse(td.getT());

    }

    //前序遍历、中序遍历、后序遍历、按层遍历。（先根遍历、中根遍历、后跟遍历）
    public static void frontOrderTraverse(TreeData td){
        if(td != null){
            //前序遍历，先根再左后右
            System.out.println(td.getData());
            frontOrderTraverse(td.getLeft());
            frontOrderTraverse(td.getRight());
        }
    }
    public static void middleOrderTraverse(TreeData td){
        if(td != null){
            //前序遍历，先根再左后右
            middleOrderTraverse(td.getLeft());
            System.out.println(td.getData());
            middleOrderTraverse(td.getRight());
        }
    }
    public static void laterOrderTraverse(TreeData td){
        if(td != null){
            //前序遍历，先根再左后右
            laterOrderTraverse(td.getRight());
            System.out.println(td.getData());
            laterOrderTraverse(td.getLeft());
        }
    }


    public void show(){
        if(t!=null){
            System.out.println(t.getData());
            TreeData left = t.getLeft();
            TreeData right = t.getRight();
            while (left!=null){
                System.out.println(left.getData());
                left = left.getLeft();

            }
        }

    }


    static final class TreeData{
        int data;
        TreeData left;
        TreeData right;
        TreeData parent;

        public int getData() {
            return data;
        }

        public TreeData getLeft() {
            return left;
        }

        public void setLeft(TreeData left) {
            this.left = left;
        }

        public TreeData getRight() {
            return right;
        }

        public void setRight(TreeData right) {
            this.right = right;
        }

        public TreeData getParent() {
            return parent;
        }

        public void setParent(TreeData parent) {
            this.parent = parent;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    private TreeData t;
    private int size;

    private TreeData getT() {
        return t;
    }

    private void setT(TreeData t) {
        this.t = t;
    }

    public int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void showDetail(){
        TreeData leftest = getLeftest(t);
        if(leftest != null){
            System.out.println(leftest.getData());
            TreeData parent = leftest.getParent();
//            while (parent!=null){
//                TreeData right = parent.getLeft();
//                if(right!=null){
//                    System.out.println(right.getData());
//                }
//                System.out.println(parent.getData());
//
//            }
        }
    }


    private TreeData getLeftest(TreeData t){
        if(t.getLeft()!=null){
            return getLeftest(t.getLeft());
        }else {
            return t;
        }
    }

    public void add(int data){
        if(t==null){
            t = new TreeData();
            t.setData(data);
        }else{
            if(t.getData() != data){
                if(t.getData() > data){
                    leftAdd(t, data);
                }
                else if(t.getData() < data){
                    rightAdd(t, data);
                }
            }
        }
    }

    private void rightAdd(TreeData td ,int data){
        if(td.getRight()==null){
            TreeData right = new TreeData();
            right.setData(data);
            right.setParent(td);
            td.setRight(right);
            size++;
        } else{
            TreeData right = td.getRight();
            if(right.getData() != data){
                if(right.getData() < data){
                    rightAdd(right, data);
                }
                else if(right.getData()>data){
                    leftAdd(right,data);
                }
            }
        }
    }

    private void leftAdd(TreeData td ,int data){
        if(td.getLeft()==null){
            TreeData left = new TreeData();
            left.setData(data);
            left.setParent(td);
            td.setLeft(left);
            size++;
        } else{
            TreeData left = td.getLeft();
            if(left.getData()!=data){
                if(left.getData()>data){
                    leftAdd(left, data);
                }
                else if(left.getData()<data){
                    rightAdd(left, data);
                }
            }
        }
    }
}
