import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFrontpageconfig } from 'app/shared/model/frontpageconfig.model';
import { FrontpageconfigService } from './frontpageconfig.service';

@Component({
    selector: 'jhi-frontpageconfig-delete-dialog',
    templateUrl: './frontpageconfig-delete-dialog.component.html'
})
export class FrontpageconfigDeleteDialogComponent {
    frontpageconfig: IFrontpageconfig;

    constructor(
        private frontpageconfigService: FrontpageconfigService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.frontpageconfigService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'frontpageconfigListModification',
                content: 'Deleted an frontpageconfig'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-frontpageconfig-delete-popup',
    template: ''
})
export class FrontpageconfigDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ frontpageconfig }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FrontpageconfigDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.frontpageconfig = frontpageconfig;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
