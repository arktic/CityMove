/*-----------------------------------------------------------
                 Masquage du Ctrl-C
-------------------------------------------------------------*/

#include <signal.h>
#include <stdio.h>

void erreur(char* msg)
  {
  fprintf(stderr,"%s\n",msg);
  exit(1);
  }

 
main(int argc,char* argv[])
  {
  int s,i;
  sigset_t ens;
  //un changement

  printf("debut processus pere\n");
  if (argc-1 != 1) {
     fprintf(stderr,"Appel : %s <duree de vie en secondes>\n",argv[0]);
     exit(1);
     }
  s=atoi(argv[1]);
/*                              construction du masque */
  sigemptyset(&ens);
  sigaddset(&ens,SIGINT);
/*                               installation du masque */
/*  sigprocmask(SIG_SETMASK,&ens,NULL); */
  if (fork()==0) {
     printf("debut processus fils\n");
     sleep(s);
     printf("fin du processus fils\n");
     }
  else {
/*     sigprocmask(SIG_SETMASK,&ens,NULL); */
    sleep(s);
    printf("fin du processus pere\n");
    }
    
  }
  
