import request from "@/utils/request";

const getBlogList = (query: Query): Promise<resBlogTag> => {
    return request.post('/blogs/getBlogs', query)
}

const getBlog = (id: bigint | number): Promise<resBlogTag> => {
    return request.get('/blogs/getBlogById', {params: {id}})
}

const getBlogsTime = (format: string): Promise<resBlogTag> => {
    // console.log(format)
    return request.get('/blogs/getBlogsTime', {params: {format}})
}

export {
    getBlogList,
    getBlog,
    getBlogsTime
}