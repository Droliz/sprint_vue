interface blogInfo {
    blog_id: number,
    title: string,
    content: string,
    author: string,
    create_time: string,
    update_time: string
}

interface Blog_tag extends blogInfo {
    tags: string | string[],   // tag,tag 需要修改为 [tag, tag] 形式
    color: string
}

interface resBlogTag extends baseRes {
    data: Blog_tag[]
}

interface Query {
    pageSize?: number,
    pageNum?: number,
    parma: {
        title?: string,
        tag?: string,
    }
}