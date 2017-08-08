import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { DachangPostModule } from './post/post.module';
import { DachangEntryModule } from './entry/entry.module';
import { DachangTagModule } from './tag/tag.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        DachangPostModule,
        DachangEntryModule,
        DachangTagModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DachangEntityModule {}
