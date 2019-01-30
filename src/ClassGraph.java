import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;

class Graph {
    int nElem; //количество вершин
    int [] [] A;  //матрица смежности
    int [] RezLen;  //массив длин всех путей
    int[][] RezMatr;  //матрица всех путей
    int kolp;  //количество путей
    int start1;  //начало ребра, через которое проходит путь
    int final1; //конец ребра, через которое проходит путь
    int min;   //длина кратчайшего пути
    int [] path;  //кратчайший путь
    int t1;  // вершина, через которую проходит путь

    Graph() {        nElem = 0;    };

    void printA() { //вывод матрицы смежности
        for (int i = 1; i<=nElem; i++) {
            System.out.println();
            for (int j = 1; j<=nElem; j++)
                System.out.print(" " + A[i][j]);      }      System.out.println();    }

    void printRezMatr() { //вывод матрицы путей
        System.out.println("Матрица-результат");
        System.out.println("Kolp=" + kolp);
        for (int k = 1; k<=kolp; k++) {
            for (int i = 1; i<=RezLen[k]; i++)
                for (int j = 1; j<=kolp; j++)
                    System.out.print(" " + RezMatr[i][j]);      }
        System.out.println();    }

    public void SetGraph(int n) { //задаем количество вершин, обнуляем матрицу смежности
        nElem = n;
        A = new int [nElem+1][nElem+1];
        for(int i = 1; i <= nElem; i++) {
            for(int j = 1; j <= nElem; j++)
                A[i][j] = 0;        }
        kolp = 0;    }

    public void joinElem(int i, int j) { //добавляем ребра в матрицу смежности
        A[i][j] = 1;        A[j][i] = 1;    }

    public boolean isNeib(int arr[],int len, int s1, int s2 ) { //проверяем, являются ли нужные вершины соседями в пути
        for (int i = 1; i <= len; i++)
            if (arr[i] == s1 && arr[i + 1] == s2)                return true;
        return false;    }

    public int minPath() { //ищем кратчайший путь среди всех путей, соответствующих условиям задачи
        min = 1000;
        int indexmin = 1;
        for (int i = 1; i<=kolp; i++) {
            if (RezLen[i] < min) {
                min = RezLen[i];
                indexmin = i;        }      }
        return indexmin;    }

    public void printMinPath(int i) {  //выводим кратчайший путь
        System.out.println("Кратчайший путь: ");
        path = new int[RezLen[i]+1];
        for (int j=1; j<=RezLen[i]; j++) {
            System.out.print(RezMatr[i][j] + " ");
            path[j] = RezMatr[i][j];      }
        System.out.println(Arrays.toString(path));    }

    public void AllPathes(int vn, int vk, int t, int start, int finall) { //ищем все пути и кратчайший
        int L = 1;      start1 = start;    final1 = finall;    t1 = t;
        int i;      int j;      int v = 0;      int Pr = 0;      int Ptr = 0;      kolp = 0;      int M[];
        M = new int[nElem+1];      int St[];      St = new int[nElem+20];
        RezMatr = new int [20][nElem+10];
        RezLen = new int[20]; //длины путей
        for (j = 1; j <=nElem; j++) //обнуляем метки
            M[j] = 0;
        int ks = 1; //верхушка стека, указатель
        St[ks] = vn;      M[vn] = 1;

        while (ks > 0) {
            Pr = 0; //Признак: смежная и c отметкой вершина
            v = St[ks]; //текущая вершина
            for (j = L; j<=nElem; j++) {  //цикл поиска смежной вершины
                if (A[v][j] == 1)
                    if (j==vk) { //если конечная вершина
                        St[ks+1] = j; //добавляем конечную вершину в стек
                        Ptr = 0; //путь не содержит вершину и ребро
                        for (i = 1; i<=ks; i++) { //? <=
                            if (St[i] == t)
                                if (isNeib(St, ks, start1, final1) == true) //путь содержит ребро
                                    Ptr = 1; }    //путь содержит вершину и ребро

                        if (Ptr==1) {
                            kolp= kolp+1;
                            for (i =1; i<=ks; i++)  RezMatr[kolp][i]=St[i];
                            RezMatr[kolp][ks+1]=vk;
                            RezLen[kolp]=ks+1;
                            St[ks+1]=0;  }   }
                    else   //если вершина не конечная

                if (M[j] == 0) { //вершина не просмотрена
                    System.out.println("Вершина " + j );
                    Pr=1;   //Признак: смежная и без отметки
                    break;   //выходим записывать в стек
                }        }

            if (Pr==1) { //записываем вершину в стек
                ks = ks+1;      St[ks] = j;  L=1;  M[j] = 1;       }
            else {  //исключение текущей вершины из стека
                L = v+1;  M[v] = 0;  St[ks]=0;  ks = ks-1;        }     }
        int indexmin;
        indexmin = minPath();  //ищем кратчайший путь среди всех возможных путей
        System.out.println("Индекс минимального пути: " + indexmin);
        printMinPath(indexmin);
        System.out.println("Длина минимального пути: " + min);    }
    public int getSize() {        return nElem;    }
    public int isJoin(int i,int j){ // проверяем смежные ли вершины
        return A[i][j];    } }
public class ClassGraph {
    public static void main(String[] args){
        MenuFrame f = new MenuFrame();
        f.setTitle("Лабораторная работа №2");
        f.setSize(800,600);
        f.setVisible(true);    } }