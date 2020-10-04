package Tester;

import Tree.Tree;

public class TesterTree {
    static Tree tree = new Tree();
    public static void main(String[] args) {
//        tree.add("2");
//        tree.add("1");
//        tree.add("3");
//        tree.add("4");
//        tree.add("5");



        tree.add("6");
        tree.add("1");
        tree.add("8");
        tree.add("7");
        tree.add("9");
    //  tree.add("5");
//        tree.preorder(tree.getRoot());
//        tree.remove("8");
//        System.out.println(tree.getRoot());

        Tree tree2 = new Tree();

        tree2.add(8);
        tree2.add(6);
        tree2.add(7);
        tree2.add(4);

        tree2.preorder(tree2.getRoot());
        System.out.println(tree2.getRoot());
        tree2.rightRotate(tree2.getRoot());
        System.out.println(tree2.getRoot());
        tree2.preorder(tree2.getRoot());



    }
}
