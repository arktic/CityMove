/*-----------------------------------------------------------
        Reprise de l'exécution dans fonctions actives
-------------------------------------------------------------*/

#include <signal.h>
#include <setjmp.h>
#include <stdio.h>

#define PROF_RECURSIVITE 5   

sigjmp_buf etatPile[PROF_RECURSIVITE+1]; /* on se sert pas de [0] */

enum reponse_sigsetjmp {NORMAL = 0,BRANCHEMENT = 1};

void traitant(int num)
  {
  int niv;
  do {
     printf("Niveau de retour ? ");
     scanf("%d",&niv);
     }
  while (niv<1 || niv>=PROF_RECURSIVITE);  /* verif bornes */
  siglongjmp(etatPile[niv],BRANCHEMENT);  
  }

void f(int niv)
  {
  int DouVientOn;
  printf("debut f : niveau %d\n",niv);
  if   (niv<PROF_RECURSIVITE) {
     DouVientOn=sigsetjmp(etatPile[niv],0);
     if (DouVientOn == NORMAL) f(niv+1);
     }
  else {
     printf("attente de SIGINT ...\n");
     pause();            
     }
  printf("fin f : niveau %d\n",niv);
  }
    
main(int argc,char* argv[])
  {
  signal(SIGINT,traitant);
  f(1);
  }
  
