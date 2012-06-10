/*-----------------------------------------------------------
               Echec appel systeme
               Forcer la reprise
-------------------------------------------------------------*/


#include <signal.h>
#include <stdio.h>
#include <unistd.h>

#define LGMAX 256

void erreur(char* msg)
  {
  fprintf(stderr,"%s\n",msg);
  exit(1);
  }

void traitant(int num) 
  {printf("---- passage par traitant ----\n");}

void lectureChaine(int i) 
  {
  char tampon[LGMAX];
  int res,j ;
  for (j=0;j<LGMAX;j++) tampon[j]=' ';  /* init */
  printf("lecture chaine %d ?\n",i);
  res=scanf("%s",tampon);
  printf("  -> res = %d, ligne %d = %s\n",res,i,tampon);
  }
    
main(int argc,char* argv[])
  {
  struct sigaction action;

  signal(SIGINT,traitant);
  lectureChaine(1);
  lectureChaine(2);

/*             preparations donnees pour reinstallation traitant */

  action.sa_handler = traitant;
  sigemptyset(&action.sa_mask);
  action.sa_flags = SA_RESTART;  /* non defini dans POSIX, */
                                 /* force reprise appel systeme */

  sigaction(SIGINT,&action,NULL);
  
  lectureChaine(3);
  }
  
