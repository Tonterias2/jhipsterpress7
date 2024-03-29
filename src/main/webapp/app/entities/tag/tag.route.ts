import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Tag } from 'app/shared/model/tag.model';
import { TagService } from './tag.service';
import { TagComponent } from './tag.component';
import { TagDetailComponent } from './tag-detail.component';
import { TagUpdateComponent } from './tag-update.component';
import { TagDeletePopupComponent } from './tag-delete-dialog.component';
import { ITag } from 'app/shared/model/tag.model';

@Injectable({ providedIn: 'root' })
export class TagResolve implements Resolve<ITag> {
    constructor(private service: TagService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((tag: HttpResponse<Tag>) => tag.body);
        }
        return Observable.of(new Tag());
    }
}

export const tagRoute: Routes = [
    {
        path: 'tag',
        component: TagComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterPress07App.tag.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tag/:id/view',
        component: TagDetailComponent,
        resolve: {
            tag: TagResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterPress07App.tag.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tag/new',
        component: TagUpdateComponent,
        resolve: {
            tag: TagResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterPress07App.tag.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tag/:id/edit',
        component: TagUpdateComponent,
        resolve: {
            tag: TagResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterPress07App.tag.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tagPopupRoute: Routes = [
    {
        path: 'tag/:id/delete',
        component: TagDeletePopupComponent,
        resolve: {
            tag: TagResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterPress07App.tag.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
