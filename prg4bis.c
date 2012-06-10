/*-----------------------------------------------------------
                Perte de signaux 
                
        "le fils envoie NbaEnvoyer signaux au pere"

        -> on introduit un accuse de reception
                
-------------------------------------------------------------*/

#include <signal.h>
#include <stdio.h>

/* un seul sert dans chaque processus, il faudrait sinon creer */
/* un autre fichier pour le fils et faire un exec */

#define NBaEnvoyer 100

int nbEnvoye=0, nbRecu=0;  
int pidfils;
                   
void traitantPere(int num)
  {
  nbRecu++;
  printf("Nb recus : %d\n",nbRecu);
  kill(pidfils,SIGUSR1);          /* accuse de reception */
  }

void traitantFils(int num) {}     /* indispensable */
  
main(int argc,char* argv[])
  {
  int res;
  sigset_t ens;
  
  /* --------------- masquage avant installation traitants */
   
  sigfillset(&ens);
  sigprocmask(SIG_SETMASK,&ens,NULL);
  sigemptyset(&ens);
  
  /* --------------le programme fait son boulot */

  res=fork();
  if (res>0) {                           /* pere */
     signal(SIGUSR1,traitantPere);
     pidfils=res;
     sigprocmask(SIG_SETMASK,&ens,NULL); /* on peut recevoir les IT */
     while (wait(NULL)==-1);                
     printf("-- fin du pere : %d signaux recus\n",nbRecu);
     }
  else if (res==0) {                               /* fils   */                     
     signal(SIGUSR1,traitantFils);  
     sigprocmask(SIG_SETMASK,&ens,NULL); /* on peut recevoir les IT */

/*              on minimise volontairement le laps de temps */
/*              entre kill et pause  */

     while (nbEnvoye < NBaEnvoyer)  {
        int i;
        nbEnvoye++;
        kill(getppid(),SIGUSR1);  
        for (i=0;i<100000;i++) ;     /* pour mettre en evidence interblocage a coup sur */
        pause();                   /* attend accuse de reception */
        printf("Nb envoyes : %d\n",nbEnvoye);
        }       
     printf("-- fin du fils : %d signaux envoyes\n",nbEnvoye);
     }
  
  }
  
