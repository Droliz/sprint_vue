import request from "@/utils/request";

const getBlogList = (query: Query): Promise<resBlogTag> => {
    return request.post('/blogs/getBlogs', query)
}

const getBlog = (id: number): Promise<resBlogTag> => {
    return request.get('/blogs/getBlogById', {params: {id}})
}

export {
    getBlogList,
    getBlog
}