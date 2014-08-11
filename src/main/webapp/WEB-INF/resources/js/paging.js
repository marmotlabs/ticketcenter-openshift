function setPagingAndSubmitForm(page, size) {
    $('#pagingPage').val(page);
    $('#pagingSize').val(size);
    $('#searchForm').submit();
}
;
