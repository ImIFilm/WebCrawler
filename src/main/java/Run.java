public class Run {
    public static void main(String[] args){
        Parser par = new Parser ("http://onet.pl/", "marsz");
        String title = par.getTitle();
        System.out.println(title);
        //par.showLinks(11);
        //par.showWords(33);
    }


}
