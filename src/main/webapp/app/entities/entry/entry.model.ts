import { BaseEntity } from './../../shared';

export class Entry implements BaseEntity {
    constructor(
        public id?: number,
        public title?: string,
        public content?: any,
        public data?: any,
        public post?: BaseEntity,
        public tags?: BaseEntity[],
    ) {
    }
}
