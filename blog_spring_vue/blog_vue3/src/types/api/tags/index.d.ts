
interface tagInfo {
    tagId: bigint,
    tag: string,
    color: string
}

interface TagsRes extends baseRes{
    data: tagInfo[],
}