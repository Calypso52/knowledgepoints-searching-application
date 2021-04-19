<template>
    <div>
        <Indexheader/>
        <Indexmain/>
        <!-- <Indexfooter/> -->
    </div>
</template>

<script>
import Indexheader from './Indexheader'
import Indexmain from './Indexmain'
import Indexfooter from './Indexfooter'

    export default {
        components: {
            Indexheader,
            Indexmain,
            Indexfooter
        }
    }
</script>

<style scoped>
    
</style>