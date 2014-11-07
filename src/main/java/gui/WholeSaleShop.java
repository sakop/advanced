package gui;

import java.util.Observable;
import java.util.Observer;

public class WholeSaleShop extends Observable{

    public void inStock(int income){
        System.out.println("进了" + income + "个货");
        setChanged();
        notifyObservers(income);
    }

    public static void main(String[] args) {
        WholeSaleShop whole = new WholeSaleShop();
        Retailers ret1 = new Retailers("老王");
        Retailers ret2 = new Retailers("田中");
        whole.addObserver(ret1);
        whole.addObserver(ret2);

        whole.inStock(10);
    }

    public String getName(){
        return "zhudi市场";
    }

}

class Retailers implements Observer{

    private String name;

    public Retailers(String name){
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        WholeSaleShop whole = (WholeSaleShop)o;
        System.out.println("喂喂，我是" + name + "听说" + whole.getName() + "进了" + arg.toString() + "个活");
    }

}