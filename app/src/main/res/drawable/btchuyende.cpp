#include<stdio.h>
// #include<conio.h>
#define max 100
struct SoNguyen{
	int a[50];
	int n;
	float kq;
};
//khai bao queue
struct Queue{
	int head,tail,count;
	SoNguyen node[max];
};


//khoi tao hang doi rong
void init(Queue &Q){
	Q.head=0;
	Q.tail=-1;
	Q.count=0;
}
// kiem tra hang doi queue rong
int isEmpty(Queue Q){
	if(Q.count==0)
		return 1;
	return 0;
}
//kiem tra hang doi day
int isFull(Queue Q){
    if(Q.count==max)
        return 1;
    return 0;
}
//them phan tu vao cuoi queue
void push(Queue &Q, SoNguyen x) //them phan tu vao cuoi Queue
{
    if (isFull(Q)) printf("Hang doi day !");
    else
    {
        Q.node[++Q.tail] = x; //tang Rear len va gan phan tu vao
        Q.count++; //tang so phan tu len
    }
}
void nhap(SoNguyen &x)
{
    int n ,a[50];
    printf("\nNhap so phan tu cua 1 day: ");
    scanf("%d",&x.n);
    for(int i=0;i<x.n;i++)
    {
        printf("\nNhap a[%d]=",i);
        scanf("%d",&x.a[i]);
    }

// x.a=a;x.n=n;
}
void nhapday(Queue &Q, int m){
    int i;

    // printf("\nCo bao nhieu day so nguyen: ");
    // scanf("%d",&m);
    for(i=1;i<=m;i++){
        SoNguyen x;
        printf("\nDay thu %d :\n",i);
        nhap(x);
        push(Q,x);
    }
}
void inday(Queue Q)
{
    if (isEmpty(Q))
        printf("\nHang doi rong !");
    else
    {
        /*
        printf("\nDen day");
        printf("%d ", Q.head);
        printf("%d %d", Q.tail, Q.count);
        */

        for (int i=Q.head; i<=Q.tail; i++)
        {
            printf("\nDay thu %d:",i);
            for(int j=0;j<Q.node[i].n;++j) {
            printf("%d ",Q.node[i].a[j]);
            // printf("\n");
            Q.head++;
        }
        //printf("\n---------------------------------------------------------------------\n");
        }
    }
}
int main()
{
    //int m;
    SoNguyen x;
    Queue Q;
    int m;
    init(Q);
    printf("\nCo bao nhieu day so nguyen: ");
    scanf("%d",&m);
    nhapday(Q,m);
    inday(Q);
// getch();
}
