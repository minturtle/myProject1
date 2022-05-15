package service;

import domain.Category;
import domain.Menu;
import domain.Restraurent;

import java.util.*;

public class RestService {
    public Collection<Restraurent> findRestByCategory(Category category){
        return initTestSet();
    }

    private Collection<Restraurent> initTestSet() {

        Restraurent bbq = new Restraurent(0L, "BBQ", null, new HashSet<>(), Category.치킨);

        bbq.getMenuList().add(new Menu(16L, "황금올리브치킨", 20000, bbq));
        bbq.getMenuList().add(new Menu(17L, "핫황금올리브치킨", 21000, bbq));
        bbq.getMenuList().add(new Menu(18L, "황금올리브닭다리", 21000, bbq));
        bbq.getMenuList().add(new Menu(19L, "자메이카통다리치킨", 21500, bbq));
        bbq.getMenuList().add(new Menu(20L, "크런치버터치킨", 24000, bbq));
        bbq.getMenuList().add(new Menu(21L, "황금올리브치킨콤보", 24000, bbq));


        Set<Restraurent> restraurentLi = new HashSet<>();
        restraurentLi.add(bbq);
        restraurentLi.add(new Restraurent(1L, "교촌치킨", null,new ArrayList<>(),  Category.치킨));
        restraurentLi.add(new Restraurent(2L, "자담치킨", null,new ArrayList<>(),  Category.치킨));
        restraurentLi.add(new Restraurent(3L, "노랑통닭", null,new ArrayList<>(),  Category.치킨));
        restraurentLi.add(new Restraurent(4L, "네네치킨", null,new ArrayList<>(),  Category.치킨));
        restraurentLi.add(new Restraurent(5L, "굽네치킨", null,new ArrayList<>(),  Category.치킨));
        restraurentLi.add(new Restraurent(6L, "호식이 두마리 치킨", null,new ArrayList<>(),  Category.치킨));
        restraurentLi.add(new Restraurent(7L, "후라이드 참 잘하는 집", null,new ArrayList<>(),  Category.치킨));
        restraurentLi.add(new Restraurent(8L, "노랑통닭", null,new ArrayList<>(),  Category.치킨));
        restraurentLi.add(new Restraurent(9L, "스모프 치킨", null,new ArrayList<>(),  Category.치킨));
        restraurentLi.add(new Restraurent(10L, "오태식 해바라기 치킨", null,new ArrayList<>(),  Category.치킨));
        restraurentLi.add(new Restraurent(11L, "ㅁㅁ치킨", null,new ArrayList<>(),  Category.치킨));
        restraurentLi.add(new Restraurent(12L, "60계 치킨", null,new ArrayList<>(),  Category.치킨));
        restraurentLi.add(new Restraurent(13L, "ㅇㅇ치킨", null,new ArrayList<>(),  Category.치킨));
        restraurentLi.add(new Restraurent(14L, "ㅈㅈ치킨", null,new ArrayList<>(),  Category.치킨));
        restraurentLi.add(new Restraurent(15L, "60계 치킨", null,new ArrayList<>(),  Category.치킨));

        return restraurentLi;
    }
}
