package combinatory;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Set<T> {

    private List<T> _list;

    public Set(int capacity) {
        _list = new ArrayList<T>(capacity);
    }
    public Set(){
        _list = new ArrayList<T>(0);
    }

    public int cardinal(){
        return _list.size();
    }

    public List<T> get_list() {
        return _list;
    }

    public int multiply(Set<T> list){
        return (_list.size() * list.cardinal());
    }

    /*public List<Integer> intersection(List<Integer> list) {
        List<Integer> aux = new ArrayList<>();
        for (Integer integer : list) {
            if (_list.contains(integer)) {
                aux.add(integer);
            }
        }
        return aux;
    }*/

    public List<T> intersection(Set<T> list) {
        List<T> aux = new ArrayList<>();
        for (T integer : list.get_list()) {
            if (_list.contains(integer)) {
                aux.add(integer);
            }
        }
        return aux;
    }

    public int difference(Set<T> list){
        return (_list.size() - intersection(list).size());
    }

    public List<T> union(Set<T> list) {
        List<T> aux = _list;
        aux.addAll(list.get_list());
        LinkedHashSet<T> union = new LinkedHashSet<>(aux);
        return new ArrayList<>(union);
    }
    public double partof(){
        return Math.pow(2, _list.size());
    }

    public double arrow(Set<T> b) {     // |A->B|
        return Math.pow(b.cardinal(), _list.size());
    }

    public double discontinious_arrow(Set<T> b) {
        return Math.pow((b.cardinal() + 1), _list.size());
    }

}
