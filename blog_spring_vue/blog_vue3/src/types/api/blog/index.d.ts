interface blogInfo {
    blogId: bigint,
    title: string,
    content: string,
    author: string,
    createTime: string,
    updateTime: string
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

interface BlogGroup {
    key: keyof Blog_tag,
    blog: Blog_tag[]
}