import { Component, OnInit, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector:'app-alert-dialog',
  template: `
   <h2 mat-dialog-title>{{data.title}}</h2>
        <mat-dialog-content>
           {{data.message}}
        </mat-dialog-content>
    <mat-dialog-actions>
        <table cellspacing="15">
           <tr>
             <td>
                 <button mat-raised-button (click)="onClickOkButton()">OK</button>
             </td>
             <td>
                 <button mat-raised-button mat-dialog-close="cancel">キャンセル</button>
             </td>
           </tr>
        </table>

    </mat-dialog-actions>
  `
})
export class AlertDialogComponent  { 
    
    constructor(
        @Inject(MAT_DIALOG_DATA) public data : any,
        public matDialogRef : MatDialogRef<AlertDialogComponent>) { }
    
    
    onClickOkButton():void {
        // OKボタンが押されたときは「OK」を呼び出し元に渡す。
        this.matDialogRef.close('OK');
    }

}
